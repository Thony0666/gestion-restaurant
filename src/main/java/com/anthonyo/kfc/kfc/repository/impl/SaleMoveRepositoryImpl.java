package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.dtos.responses.SaleMovResponse;
import com.anthonyo.kfc.kfc.dtos.responses.SaleResponse;
import com.anthonyo.kfc.kfc.entities.DetailMenu;
import com.anthonyo.kfc.kfc.entities.SaleMov;
import com.anthonyo.kfc.kfc.repository.SaleMovRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SaleMoveRepositoryImpl implements SaleMovRepository {
    private final Connection connection;

    @Override
    public List<SaleMovResponse> findBetweenDate(Instant startDate, Instant endDate) {
        var saleMove = new ArrayList<SaleMovResponse>();
        try {
            var ps = connection.prepareStatement("""
                     WITH sale_mov AS (
                         SELECT
                             s.restaurant_id,
                             s.menu_id,
                             SUM(s.price) AS totalPrice,
                             SUM(s.quantity) AS nbSold
                         FROM sale s
                         WHERE s.sale_date BETWEEN ? AND ?
                        GROUP BY s.restaurant_id, s.menu_id
                    )
                     SELECT
                        sm.restaurant_id,
                         r.place,
                         m.name,
                         sm.nbSold,
                         sm.totalPrice
                     FROM sale_mov sm
                             JOIN restaurant r ON r.id = sm.restaurant_id
                              JOIN menu m ON m.id = sm.menu_id order by sm.restaurant_id;
                    """);
            ps.setTimestamp(1, Timestamp.from(startDate));
            ps.setTimestamp(2,Timestamp.from(endDate));
            var result = ps.executeQuery();
            while (result.next()){
                saleMove.add(
                        SaleMovResponse.builder()
                                .restaurantId(result.getInt("restaurant_id"))
                                .restaurantName(result.getString("place"))
                                .detailMenuList(Collections.singletonList(DetailMenu.builder()
                                        .menu(result.getString("name"))
                                        .sumQte(result.getDouble("nbsold"))
                                        .totalPrice(result.getInt("totalprice"))
                                        .build()))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return saleMove;
    }
}
