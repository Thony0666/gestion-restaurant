package com.anthonyo.kfc.kfc.connection;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "database")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DatabaseSettings {
     String url;
     String username;
     String password;
}
