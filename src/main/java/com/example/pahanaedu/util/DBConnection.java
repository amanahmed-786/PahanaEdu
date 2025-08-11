package com.example.pahanaedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/PahanaEduDB";
    private static final String USER = "admin";
    private static final String PASS = "admin123"; // use your MySQL password here

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
