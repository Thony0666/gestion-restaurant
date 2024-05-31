package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.entities.Menu;
import com.anthonyo.kfc.kfc.entities.Sale;
import com.anthonyo.kfc.kfc.repository.SaleRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.Instant;

@Repository
public class SaleRepositoryImpl implements SaleRepository {
    private final Connection connection;

    public SaleRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Sale creatSale(Sale toCreat) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into sale(price,menu_id,restaurant_id,sale_date,quantity)  values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS );
            ps.setInt(1,toCreat.getPrice());
            ps.setInt(2,toCreat.getMenu().getId());
            ps.setInt(3,toCreat.getRestaurant().getId());
            ps.setTimestamp(4, Timestamp.from(Instant.now()));
            ps.setInt(5,toCreat.getQuantity());
            var result=ps.executeUpdate();
            if (result>0)
            {
                var rs = ps.getGeneratedKeys();
                if (rs.next()){
                    toCreat.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toCreat;
    }
}
