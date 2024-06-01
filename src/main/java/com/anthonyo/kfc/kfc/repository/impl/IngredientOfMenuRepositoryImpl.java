package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.dtos.requests.IngredientOneOfMenu;
import com.anthonyo.kfc.kfc.dtos.requests.UpdateQteIOFRequest;
import com.anthonyo.kfc.kfc.dtos.responses.IOMResponse;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.IngredientOfMenu;
import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.repository.IngredientOfMenuRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientOfMenuRepositoryImpl implements IngredientOfMenuRepository {
    private final Connection connection;

    public IngredientOfMenuRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public IngredientOfMenu create(IngredientOfMenu toCreate) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into  ingredient_of_menu (quantity,menu_id,ingredient_id) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, toCreate.getQuantity());
            ps.setInt(2, toCreate.getMenu().getId());
            ps.setInt(3, toCreate.getIngredient().getId());
            var result = ps.executeUpdate();
            if (result > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    var id = rs.getInt(1);
                    toCreate.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toCreate;
    }

    @Override
    public IngredientOneOfMenu createOneToOne(IngredientOneOfMenu toCreate) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into  ingredient_of_menu (quantity,menu_id,ingredient_id) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, toCreate.getQuantity());
            ps.setInt(2,toCreate.getIdMenu());
            ps.setInt(3,toCreate.getIdIngredient());
            var result = ps.executeUpdate();
            if (result > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    var id = rs.getInt(1);
                    toCreate.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toCreate;
    }

    @Override
    public Optional<IngredientOfMenu> findByManyID(Integer menuId, Integer ingredientId) {
        try {
            var ps = connection.prepareStatement("select * from ingredient_of_menu where menu_id =? and ingredient_id=?");
            ps.setInt(1, menuId);
            ps.setInt(2, ingredientId);
            var result = ps.executeQuery();

            if (result.next()) {
                return Optional.of(IngredientOfMenu.builder()
                        .id(result.getInt("id"))
                        .quantity(result.getDouble("quantity"))
                        .menu(Menu.builder().id(result.getInt("menu_id")).build())
                        .ingredient(Ingredient.builder().id(result.getInt("ingredient_id")).build())
                        .build());
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UpdateQteIOFRequest updateQteIOFRequest) {
        try {
            var ps = connection.prepareStatement("update ingredient_of_menu set quantity=? where menu_id=? and ingredient_id=? ");
            ps.setDouble(1, updateQteIOFRequest.getNewQte());
            ps.setInt(2, updateQteIOFRequest.getMenuId());
            ps.setInt(3, updateQteIOFRequest.getIngredientId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<IngredientOfMenu> findByIdMenu(Integer menuId) {
        var iom = new ArrayList<IngredientOfMenu>();
        var sql = "select * from ingredient_of_menu where menu_id=?";
        try {
            var ps = connection.prepareStatement(sql);
            ps.setInt(1, menuId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                iom.add(
                        IngredientOfMenu.builder()
                                .id(rs.getInt("id"))
                                .quantity(rs.getDouble("quantity"))
                                .ingredient(Ingredient.builder()
                                        .id(rs.getInt("ingredient_id"))
                                        .build())
                                .quantity(rs.getDouble("quantity"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return iom;
    }

    @Override
    public List<IOMResponse> findAllByMenuId(Long menuId) {
        var iom = new ArrayList<IOMResponse>();
        var sql = """
                select i.name ,i.id,im.quantity,u.name as unit
                         from ingredient_of_menu im
                 join ingredient i on i.id = im.ingredient_id
                 join unit u on u.id = i.unit_id
                     where menu_id =?
                order by im.quantity desc;
                """;
        try {
            var ps = connection.prepareStatement(sql);
            ps.setLong(1, menuId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                iom.add(
                        IOMResponse
                                .builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .quantity(rs.getDouble("quantity"))
                                .unit(rs.getString("unit"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return iom;
    }

    @Override
    public Optional<IOMResponse> deleteByManyId(Integer menuId, Integer ingredientId) {
        try {
            var ps = connection.prepareStatement("delete from ingredient_of_menu where menu_id=? and ingredient_id= ?;");
            ps.setInt(1, menuId);
            ps.setInt(2, ingredientId);
            var result = ps.executeUpdate();
            if (result > 0) {
                return Optional.of(IOMResponse.builder().id(result).build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
