package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.entities.Restaurant;
import com.anthonyo.kfc.kfc.repository.RestaurantRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private final Connection connection;

    public RestaurantRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Restaurant create(Restaurant toCreate) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into restaurant (name,place) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, toCreate.getName());
            ps.setString(2,toCreate.getPlace());
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
}
