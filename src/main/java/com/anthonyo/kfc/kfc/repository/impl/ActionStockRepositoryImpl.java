package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.dtos.responses.ActionStockResponse;
import com.anthonyo.kfc.kfc.entities.ActionStock;
import com.anthonyo.kfc.kfc.repository.ActionStockRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ActionStockRepositoryImpl implements ActionStockRepository {
    private final Connection connection;

    public ActionStockRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ActionStock create(ActionStock toCreate) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into action_stock (quantity,type,action_date,ingredient_id,stock_id) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, toCreate.getQuantity());
            ps.setString(2, toCreate.getType().name());
            ps.setTimestamp(3, Timestamp.from(toCreate.getDateTime()));
            ps.setInt(4, toCreate.getIngredient().getId());
            ps.setInt(5, toCreate.getStock().getId());
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
    public List<ActionStockResponse> findAll() {
        List<ActionStockResponse> actionStocks = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT action_stock.id,action_date,type,quantity,ingredient.name AS ingredient,unit.name AS unit FROM action_stock  INNER JOIN ingredient  ON action_stock.id = ingredient.id INNER JOIN unit ON ingredient.id = unit.id");
            ps.executeQuery();
            var rs = ps.executeQuery();
            while (rs.next()) {
                Instant instant = rs.getTimestamp("action_date").toInstant();
                actionStocks.add(
                        ActionStockResponse.builder()
                                .actionDate(instant)
                                .quantity(rs.getDouble("quantity"))
                                .type(rs.getString("type"))
                                .ingredient(rs.getString("ingredient"))
                                .unit(rs.getString("unit"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actionStocks;
    }

    @Override
    public List<ActionStockResponse> findActionsBetweenDates(LocalDate fromDate, LocalDate toDate) {
        var query = """
                SELECT action_stock.id, action_date, type, quantity, ingredient.name AS ingredient, unit.name AS unit
                        FROM
                    action_stock a
                        INNER JOIN
                    ingredient ON action_stock.ingredient_id = ingredient.id
                        INNER JOIN
                    unit ON ingredient.unit_id = unit.id
                        INNER JOIN
                    stock s ON a.stock_id = s.id
                        INNER JOIN
                    of s ON a.stock_id = s.id
                        WHERE
                action_date BETWEEN ? AND ?
                """;
        List<ActionStockResponse> actionStocks = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDate(1, Date.valueOf(fromDate));
            ps.setDate(2, Date.valueOf(toDate));
            var rs = ps.executeQuery();
            while (rs.next()) {
                Instant instant = rs.getTimestamp("action_date").toInstant();
                actionStocks.add(
                        ActionStockResponse.builder()
                                .actionDate(instant)
                                .quantity(rs.getDouble("quantity"))
                                .type(rs.getString("type"))
                                .ingredient(rs.getString("ingredient"))
                                .unit(rs.getString("unit"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actionStocks;
    }
}
