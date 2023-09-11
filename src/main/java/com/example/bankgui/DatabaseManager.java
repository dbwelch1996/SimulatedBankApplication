package com.example.bankgui;

import com.example.bankgui.*;

import java.sql.*;

public class DatabaseManager {
    final private String jdbcUrl = "jdbc:mysql://localhost:3306/Users";
    final private String username = "root";
    final private Config config = new Config();
    final private String password = config.getSqlPassword();

    private static Connection connection = null;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String username, String password) {
        String sql = "INSERT INTO UserData (username, password, money) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 0); // Set money to 0

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                connection.commit(); // Commit the transaction
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Data insertion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDatabase() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isPasswordCorrect(String username, String password) {
        String sql = "SELECT COUNT(*) FROM UserData WHERE username = ? AND password = ?";

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
    public double getMoneyByUsername(String username) {
        String sql = "SELECT money FROM UserData WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if there was an error or the user doesn't exist
    }
    public boolean updateMoneyByUsername(String username, double newMoney) {
        String sql = "UPDATE UserData SET money = ? WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, newMoney);
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                connection.commit(); // Commit the transaction
                return true; // Money updated successfully
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Money update failed
    }
    public boolean insertTransaction(String username, String transactionType, double transactionAmount, String Date) {
        String sql = "INSERT INTO TransactionHistory (username, transactionType, amount, date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, transactionType);
            preparedStatement.setDouble(3, transactionAmount);
            preparedStatement.setString(4, Date);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int transactionID = generatedKeys.getInt(1); // Get the auto-incremented transaction ID
                    connection.commit(); // Commit the transaction
                    System.out.println("Transaction ID: " + transactionID); // Print the generated transaction ID
                    return true; // Transaction inserted successfully
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Transaction insert failed
    }


    public String getAllTransactionsByUsername(String username) {
        String sql = "SELECT transactionType, amount, Date FROM TransactionHistory WHERE username = ?";
        StringBuilder transactionDetails = new StringBuilder();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String transactionType = resultSet.getString("transactionType");
                double transactionAmount = resultSet.getDouble("amount");
                String transactionDate = resultSet.getString("date");

                // Determine whether it's a deposit or withdrawal
                String sign = (transactionType.equalsIgnoreCase("withdraw")) ? "-" : "+";

                // Format the transaction details
                String formattedTransaction = "Date: " + transactionDate + ", " + transactionType + ": " + sign + "$" + transactionAmount + "\n";

                // Append to the result string
                transactionDetails.append(formattedTransaction).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactionDetails.toString();
    }
}