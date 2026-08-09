package filefix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWriter {

    public static void insertExpense(
            String source,
            double amount,
            int yearlyFrequency) {

        String sql = """
                INSERT INTO Expense
                (source, amount, yearlyfrequency)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, source);
            statement.setDouble(2, amount);
            statement.setInt(3, yearlyFrequency);
            statement.executeUpdate();

            System.out.println("Expense inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertWage(
            String source,
            double amount,
            int month) {

        String sql = """
                INSERT INTO Wage
                (source, amount, month)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, source);
            statement.setDouble(2, amount);
            statement.setInt(3, month);
            statement.executeUpdate();

            System.out.println("Wage inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertExpense("Food", 25.50, 52);
        insertWage("Babysitting", 200.00, 8);
    }
}