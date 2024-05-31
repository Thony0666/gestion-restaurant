package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.dtos.responses.IngredientWithUnit;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Unit;
import com.anthonyo.kfc.kfc.repository.IngredientRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {
    private final Connection connection;

    public IngredientRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Ingredient create(Ingredient toCreate) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into ingredient(name,price,unit_id) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, toCreate.getName());
            ps.setInt(2, toCreate.getPrice());
            ps.setInt(3, toCreate.getUnit().getId());
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
    public Optional<Ingredient> findById(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from ingredient where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(
                        Ingredient.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .price(rs.getInt("price"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Ingredient> findAll() {
        var ingredients = new ArrayList<Ingredient>();
        try {
            var ps = connection.prepareStatement("select * from ingredient");
            var result = ps.executeQuery();
            while (result.next()) {
                ingredients.add(
                        Ingredient.builder()
                                .id(result.getInt("id"))
                                .name(result.getString("name"))
                                .price(result.getInt("price"))
                                .unit(Unit.builder()
                                        .id(result.getInt("unit_id"))
                                        .build())
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ingredients;
    }

    @Override
    public List<IngredientWithUnit> findIngredientWithUnit() {
        var ingredients = new ArrayList<IngredientWithUnit>();
        try {
            var ps = connection.prepareStatement("select i.id,i.name,u.name as unit from ingredient i join unit u on i.unit_id = u.id");
            var result = ps.executeQuery();
            while (result.next()) {
                ingredients.add(
                        IngredientWithUnit.builder()
                                .id(result.getInt("id"))
                                .name(result.getString("name"))
                                .unit(result.getString("unit"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredients;
    }
}
