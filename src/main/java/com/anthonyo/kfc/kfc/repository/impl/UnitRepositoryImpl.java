package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.entities.Unit;
import com.anthonyo.kfc.kfc.repository.UnitRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UnitRepositoryImpl implements UnitRepository {
    private final Connection connection;

    public UnitRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Unit create(Unit toCreate) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into unit(name) values (?)");
            ps.setString(1,toCreate.getName());
            var result = ps.executeUpdate();
            if (result>0){
                ResultSet rs =ps.getGeneratedKeys();
                if (rs.next() ){
                    toCreate.setId(toCreate.getId());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return toCreate;
    }

    @Override
    public List<Unit> findAll() {
        var units= new ArrayList<Unit>();
        try {
            var ps = connection.prepareStatement("select * from unit");
            var result = ps.executeQuery();
            while (result.next()){
                units.add(
                        Unit.builder()
                                .id(result.getInt("id"))
                                .name(result.getString("name"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return units;
    }
}
