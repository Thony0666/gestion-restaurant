package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.repository.MenuRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
            PreparedStatement ps = connection.prepareStatement("insert into menu(name,price) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,toCreate.getName());
            ps.setInt(2,toCreate.getPrice());
            var result = ps.executeUpdate();
            if (result>0){
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


}
