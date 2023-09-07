package com.example.bankgui;

import java.sql.*;

public class DatabaseManager {
   final private String jdbcUrl = "jdbc:mysql://localhost:3306/Users";
   final private String username = "root";
   final private Config config = new Config();
   final private  String password = config.getSqlPassword();

  private static Connection connection = null;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertData(String username, String password) {
        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 0);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Data insertion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeDataBase() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isPasswordCorrect(String username, String password) {
        String sql = "SELECT COUNT(*) FROM Users WHERE username = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // If there was an error or no match found, return false
    }

}

