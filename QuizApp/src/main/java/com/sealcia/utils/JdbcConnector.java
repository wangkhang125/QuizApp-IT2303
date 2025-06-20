package com.sealcia.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
    private static JdbcConnector instance;
    private final Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private JdbcConnector() throws SQLException {
        this.connection =
            DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "root");
    }

    public static JdbcConnector getInstance() throws SQLException {
        if (instance == null) {
            instance = new JdbcConnector();
        }
        return instance;
    }

    public Connection connect() {
        return this.connection;
    }

    public void close() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }
}
