package com.anthonyo.kfc.kfc.connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class Config {
    private  final DatabaseSettings databaseSettings;

    public Config(DatabaseSettings databaseSettings) {
        this.databaseSettings = databaseSettings;
    }

    @Bean
    public Connection getConnection()  {
        try {
            return DriverManager.getConnection(databaseSettings.getUrl(), databaseSettings.getUsername(), databaseSettings.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
