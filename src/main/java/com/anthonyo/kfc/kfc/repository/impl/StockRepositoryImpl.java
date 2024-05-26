package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Stock;
import com.anthonyo.kfc.kfc.repository.StockRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
            PreparedStatement ps = connection.prepareStatement("insert into stock (quantity,ingredient_id) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, toCreate.getQuantity());
            ps.setInt(2, toCreate.getIngredient().getId());
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
    public Stock update(Stock toUpdate) {
        try {
            PreparedStatement ps = connection.prepareStatement("update stock set quantity =? where ingredient_id=?");
            ps.setDouble(1, toUpdate.getQuantity());
            ps.setInt(2, toUpdate.getIngredient().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toUpdate;
    }

    @Override
    public Optional<Stock> findByIngredientId(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement("select  * from stock where ingredient_id=?");
            ps.setInt(1, id);
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
}
