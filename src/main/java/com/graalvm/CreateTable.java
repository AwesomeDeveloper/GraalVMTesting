package com.graalvm;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        try {

            Class.forName("org.sqlite.JDBC"); // Load the SQLite JDBC driver

            // Get the database file as a resource
            ClassLoader classLoader = CreateTable.class.getClassLoader(); // Replace YourClass with your class name
            URL resource = classLoader.getResource("/config.db");


            String url = "jdbc:sqlite:config.db";

            try (Connection connection = DriverManager.getConnection(url);
                 Statement statement = connection.createStatement()) {
                String tableSQL = "DROP TABLE IF EXISTS Config;";
                statement.execute(tableSQL);

                // SQL statement to create the table
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Config (" +
                        "    id INTEGER PRIMARY KEY DEFAULT 1," +
                        "    username VARCHAR(250) default 'Testing'," +
                        "    email VARCHAR(250) default 'Testing@gmail.com'" +
                        ");";

                // Execute the SQL statement
                statement.execute(createTableSQL);
                System.out.println("Table 'Config' created successfully.");

                String insertRecordSQL = "INSERT INTO Config DEFAULT VALUES;";

                // Create a PreparedStatement
                PreparedStatement preparedStatement = connection.prepareStatement(insertRecordSQL);
                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();
                System.out.println("Record inserted successfully. Rows affected: " + rowsAffected);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
