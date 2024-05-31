package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.entities.Restaurant;
import com.anthonyo.kfc.kfc.repository.RestaurantRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private final Connection connection;

    public RestaurantRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Restaurant create(Restaurant toCreate) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into restaurant (place) values (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,toCreate.getPlace());
            var result = ps.executeUpdate();
            if (result > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    toCreate.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toCreate;
    }

    @Override
    public List<Restaurant> findAll() {
        var restaurantList = new ArrayList<Restaurant>();
        try {
            var ps = connection.prepareStatement("select * from restaurant");
            var result = ps.executeQuery();
            while (result.next()){
                restaurantList.add(
                        Restaurant.builder()
                                .id(result.getInt("id"))
                                .place(result.getString("place"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return restaurantList;
    }
}
