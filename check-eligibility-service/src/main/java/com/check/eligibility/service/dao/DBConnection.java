package com.check.eligibility.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author crazydude
 */
public class DBConnection {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("MySQL JDBC Registered!");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance_website", "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found?");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();

        }

        if (connection != null) {
            System.out.println("Connection Successful!");
        }
        return connection;
    }

}
