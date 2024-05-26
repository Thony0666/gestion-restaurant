package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;
import com.anthonyo.kfc.kfc.repository.IngredientOfMenuRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class IngredientOfMenuRepositoryImpl implements IngredientOfMenuRepository {
    private final Connection connection;

    public IngredientOfMenuRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public IngredientOfMenu create(IngredientOfMenu toCreate) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into  ingredient_of_menu (quantity,menu_id,ingredient_id) values (?,?,?)" , Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1,toCreate.getQuantity());
            ps.setInt(2,toCreate.getMenu().getId());
            ps.setInt(3,toCreate.getIngredient().getId());
            var result = ps.executeUpdate();
            if (result > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    var id  = rs.getInt(1);
                     toCreate.setId(id);                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toCreate;
    }
}
