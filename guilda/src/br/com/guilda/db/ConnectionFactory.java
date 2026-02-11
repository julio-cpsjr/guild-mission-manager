package br.com.guilda.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:sqlite:storage.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
    public static Driver getDriver() throws ClassNotFoundException, SQLException {
        return DriverManager.getDriver(URL);
    }

}

