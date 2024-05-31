package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.repository.MenuRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MenuRepositoryImpl implements MenuRepository {
    private final Connection connection;

    public MenuRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Menu create(Menu toCreate) {
        try {
            var ps = connection.prepareStatement("insert into menu(name,price) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,toCreate.getName());
            ps.setInt(2,toCreate.getPrice());
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
    public Optional<Menu> findById(Integer id) {
        try {
            PreparedStatement ps  = connection.prepareStatement("select * from menu where id=?");
            ps.setInt(1,id);
            var result = ps.executeQuery();
            if (result.next()){
                return Optional.of(Menu.builder()
                                .id(result.getInt("id"))
                                .price(result.getInt("price"))
                                .name(result.getString("name"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Menu> findAll() {
        var menus= new ArrayList<Menu>();
        try {
            var ps = connection.prepareStatement("select * from menu");
            var result = ps.executeQuery();
            while (result.next()){
                menus.add(
                        Menu.builder()
                                .id(result.getInt("id"))
                                .price(result.getInt("price"))
                                .name(result.getString("name"))
                                .build()
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return menus;
    }


}
