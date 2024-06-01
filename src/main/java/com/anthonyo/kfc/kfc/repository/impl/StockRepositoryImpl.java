package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.dtos.requests.SupplyStockRequest;
import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;
import com.anthonyo.kfc.kfc.dtos.responses.StockResponse;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Restaurant;
import com.anthonyo.kfc.kfc.entities.Stock;
import com.anthonyo.kfc.kfc.repository.StockRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StockRepositoryImpl implements StockRepository {
    private final Connection connection;

    public StockRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Stock create(Stock toCreate) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into stock (quantity,ingredient_id,restaurant_id) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, toCreate.getQuantity());
            ps.setInt(2, toCreate.getIngredient().getId());
            ps.setInt(3, toCreate.getRestaurant().getId());
            var result = ps.executeUpdate();
            if (result > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    toCreate.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toCreate;
    }

    @Override
    public StockResponse update(Stock supplyStockRequest) {
        try {
            PreparedStatement ps = connection.prepareStatement("update stock set quantity =? where ingredient_id=? and restaurant_id =?");
            ps.setDouble(1,supplyStockRequest.getQuantity());
            ps.setInt(2,supplyStockRequest.getIngredient().getId());
            ps.setInt(3,supplyStockRequest.getRestaurant().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<Stock> findByIngredientId(SupplyStockRequest supplyStockRequest) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from stock where ingredient_id=? and restaurant_id = ?");
            ps.setInt(1, supplyStockRequest.getIngredient());
            ps.setInt(2, supplyStockRequest.getRestaurant());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        Stock.builder()
                                .id(rs.getInt("id"))
                                .quantity(rs.getDouble("quantity"))
                                .ingredient(Ingredient.builder()
                                        .id(rs.getInt("ingredient_id"))
                                        .build())
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Stock> findByIngredientIdSale(Integer ingredientId,Integer restaurantId) {
        try {
           var ps = connection.prepareStatement("select * from stock where ingredient_id = ? and restaurant_id=?;");
            ps.setInt(1,ingredientId);
            ps.setInt(2,restaurantId);
            var result=ps.executeQuery();
            if (result.next()){
              return
                       Optional.of(Stock.builder()
                               .id(result.getInt("id"))
                               .quantity(result.getDouble("quantity"))
                               .restaurant(Restaurant.builder().id(result.getInt("restaurant_id")).build())
                               .ingredient(Ingredient.builder().id(result.getInt("ingredient_id")).build())
                               .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<ActionStockResponse> findByBetweenDate(Integer restaurantId, Instant startInstant, Instant endInstant) {
        var stocks = new ArrayList<ActionStockResponse>();
        var query = """
                SELECT
                    action_stock.id,
                    action_stock.action_date as date_et_heure,
                    action_stock.type as type,
                    ingredient.name AS ingredient,
                    action_stock.quantity as quantite,
                    unit.name AS unite
                FROM stock
                    INNER JOIN
                    action_stock  ON action_stock.stock_id = stock.id
                    INNER JOIN
                    ingredient on action_stock.ingredient_id  = ingredient.id
                    INNER JOIN
                    unit ON ingredient.unit_id = unit.id where restaurant_id = ? and action_date BETWEEN ? AND ?;
                """;
        try {
            var ps = connection.prepareStatement(query);
            ps.setInt(1,restaurantId);
            ps.setTimestamp(2, Timestamp.from(startInstant));
            ps.setTimestamp(3, Timestamp.from(endInstant));
            var result = ps.executeQuery();
            while (result.next()) {
                var date  = result.getTimestamp("date_et_heure").toLocalDateTime();
                stocks.add(
                        ActionStockResponse.builder()
                                .actionDate((date))
                                .id(result.getInt("id"))
                                .type(result.getString("type"))
                                .ingredient(result.getString("ingredient"))
                                .quantity(result.getDouble("quantite"))
                                .unit(result.getString("unite"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stocks;
    }
}
