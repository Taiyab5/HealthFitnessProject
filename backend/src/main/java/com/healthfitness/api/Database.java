package com.healthfitness.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {
    private static final String DEFAULT_URL =
            "jdbc:mysql://localhost:3306/health_fitness?serverTimezone=UTC";

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        String password = System.getenv("DB_PASSWORD");
        if (password == null) {
            throw new IllegalStateException("Set the DB_PASSWORD environment variable before starting Tomcat.");
        }

        String url = environmentOrDefault("DB_URL", DEFAULT_URL);
        String username = environmentOrDefault("DB_USER", "root");
        return DriverManager.getConnection(url, username, password);
    }

    private static String environmentOrDefault(String name, String defaultValue) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
