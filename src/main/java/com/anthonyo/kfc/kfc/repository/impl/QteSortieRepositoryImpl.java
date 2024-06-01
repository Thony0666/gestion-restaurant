package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.dtos.responses.MostUsedIngredientResponse;
import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.entities.QteSortie;
import com.anthonyo.kfc.kfc.entities.Restaurant;
import com.anthonyo.kfc.kfc.repository.QteSortieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QteSortieRepositoryImpl implements QteSortieRepository {
    private final Connection connection;

    @Override
    public QteSortie create(QteSortie qteSortie) {
        try {
            var ps = connection.prepareStatement("INSERT INTO qte_sortie(quantity,ingredient,menu_id,restaurant_id,sortie_date) values (?,?,?,?,?)");
            ps.setDouble(1, qteSortie.getQuantity());
            ps.setInt(2, qteSortie.getIngredient());
            ps.setInt(3, qteSortie.getMenu().getId());
            ps.setInt(4, qteSortie.getRestaurant().getId());
            ps.setTimestamp(5, Timestamp.from(Instant.now()));
            var result = ps.executeUpdate();
            if (result > 0) {
                var rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    qteSortie.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void updateByManyId(Double quantity, Integer menuId, Integer restaurantId, Integer ingredient) {
        try {
            var ps = connection.prepareStatement("update qte_sortie set quantity=? where menu_id=? and restaurant_id=? and ingredient=?");
            ps.setDouble(1, quantity);
            ps.setInt(2, menuId);
            ps.setInt(3, restaurantId);
            ps.setInt(4, ingredient);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<QteSortie> findByManyId(Integer menu, Integer restaurant, Integer ingredient) {
        try {
            var ps = connection.prepareStatement("SELECT * FROM qte_sortie where menu_id=? and restaurant_id=? and ingredient=?");
            ps.setInt(1, menu);
            ps.setInt(2, restaurant);
            ps.setInt(3, ingredient);
            var result = ps.executeQuery();
            if (result.next()) {
                return Optional.of(
                        QteSortie.builder()
                                .id(result.getInt("id"))
                                .ingredient(result.getInt("ingredient"))
                                .quantity(result.getDouble("quantity"))
                                .menu(Menu.builder().id(result.getInt("menu_id")).build())
                                .restaurant(Restaurant.builder().id(result.getInt("restaurant_id")).build())
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<QteSortie> findAll() {
        var qteStories = new ArrayList<QteSortie>();
        try {
            var ps = connection.prepareStatement("SELECT * FROM qte_sortie");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                qteStories.add(
                        QteSortie.builder()
                                .id(result.getInt("id"))
                                .ingredient(result.getInt("ingredient"))
                                .quantity(result.getDouble("quantity"))
                                .menu(Menu.builder().id(result.getInt("menu_id")).build())
                                .restaurant(Restaurant.builder().id(result.getInt("restaurant_id")).build())
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return qteStories;
    }

    @Override
    public List<MostUsedIngredientResponse> findMostUsedIngredient(Instant startDate, Instant endDate, Integer restaurantId, Integer limit) {
        var mostUsedIngredient = new ArrayList<MostUsedIngredientResponse>();
        try {
            var ps = connection.prepareStatement("""
                                        WITH sumQte AS (SELECT qs.ingredient ,qs.menu_id,qs.restaurant_id ,SUM(qs.quantity) as total
                                                        FROM qte_sortie qs
                                                        WHERE qs.sortie_date BETWEEN ? AND ? AND restaurant_id = ?
                                                        GROUP BY qs.ingredient,qs.menu_id,qs.restaurant_id )
                                        SELECT i.id AS ingredient_id ,i.name AS ingredient,m.name as menu, sq.total as qte_expenses, u.name as unit
                    FROM sumQte AS sq JOIN menu m ON sq.menu_id = m.id
                                             JOIN ingredient i ON sq.ingredient = i.id 
                        JOIN unit u ON u.id = i.unit_id 
                    ORDER BY total DESC LIMIT ?;
                                        """);
            ps.setTimestamp(1, Timestamp.from(startDate));
            ps.setTimestamp(2, Timestamp.from(endDate));
            ps.setInt(3, restaurantId);
            ps.setInt(4, limit);
            var result = ps.executeQuery();
            while (result.next()) {
                mostUsedIngredient.add(
                        MostUsedIngredientResponse.builder()
                                .ingredient(result.getInt("ingredient_id"))
                                .ingredientName(result.getString("ingredient"))
                                .menuName(result.getString("menu"))
                                .quantityExpenses(result.getDouble("qte_expenses"))
                                .unit(result.getString("unit"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mostUsedIngredient;
    }
}
