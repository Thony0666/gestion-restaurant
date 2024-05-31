package com.anthonyo.kfc.kfc.repository.impl;

import com.anthonyo.kfc.kfc.entities.ActionStock;
import com.anthonyo.kfc.kfc.entities.Ingredient;
import com.anthonyo.kfc.kfc.entities.Stock;
import com.anthonyo.kfc.kfc.enums.ActionStockType;
import com.anthonyo.kfc.kfc.repository.ActionStockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ActionStockRepositoryImplTest {
    @Autowired
    private ActionStockRepository actionStockRepository;

    @Autowired
    private Connection connection;

    @BeforeEach
    void setUp() {
        removeTestData();
        executeSql("insert into  restaurant (id,name,place) values (1,'kfc','Ampasika')", "insert into  ingredient (id,name,price,unit_id) values (1,'Tongolo',100,1)", "insert into  stock (id,quantity,ingredient_id,restaurant_id) values (1,5.00,1,1)");
    }

    @AfterEach
    void tearDown() {
        removeTestData();
    }

    private void removeTestData() {
        executeSql("DELETE FROM  ingredient WHERE unit_id = 1", "DELETE FROM  stock WHERE restaurant_id = 1", "DELETE FROM  restaurant WHERE id = 1");
    }

    private void executeSql(String sql, String sql1, String sql2) {
        try (var statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute(sql);
            statement.execute(sql1);
            statement.execute(sql2);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Test
    @Sql({
        "classpath:create-stock-ingredient.sql"
    })
    void create() {
        var actionStock = new ActionStock();
        var stock = new Stock();
        stock.setId(2);
        actionStock.setStock(stock);
        actionStock.setQuantity(5.00);
        actionStock.setType(ActionStockType.SORTIE);
        actionStock.setIngredient(Ingredient.builder()
                        .id(1)
                .build());

        var result = actionStockRepository.create(actionStock);
        assertThat(actionStock.getId()).isGreaterThan(0);
        assertThat(result).isNotNull();
        assertThat(result.getStock()).isNotNull();
    }

    @Test
    void findAll() {
    }

    @Test
    void findActionsBetweenDates() {
    }
}