package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Stock;
import com.anthonyo.kfc.kfc.repository.IngredientRepository;
import com.anthonyo.kfc.kfc.repository.StockRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {
    private final Connection connection;
    private  final StockRepository stockRepository;

    public IngredientRepositoryImpl(Connection connection, StockRepository stockRepository) {
        this.connection = connection;
        this.stockRepository = stockRepository;
    }

    @Override
    public Ingredient create(Ingredient toCreate) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into ingredient(name,price,unit_id) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,toCreate.getName());
            ps.setInt(2,toCreate.getPrice());
            ps.setInt(3,toCreate.getUnit().getId());
            var result = ps.executeUpdate();
            if (result>0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()){
                    toCreate.setId(rs.getInt(1));
                }
                var creatStock = Stock.builder()
                        .quantity(0.00)
                        .ingredient(Ingredient.builder()
                                .id(toCreate.getId())
                                .build())
                        .build();
                stockRepository.create(creatStock);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return toCreate;
    }

    @Override
    public Optional<Ingredient> findById(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from ingredient where id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return Optional.of(
                        Ingredient.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .price(rs.getInt("price"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
