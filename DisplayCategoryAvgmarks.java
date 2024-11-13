package com.example.JDBC.jdbcpractice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
public class DisplayCategoryAvgmarks {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=test;encrypt=true;trustServerCertificate=true;";
        try (Connection con = DriverManager.getConnection(url, "SA", "Sahithi@23");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT address, AVG(marks) AS average_marks FROM students GROUP BY address")) {
            while (rs.next()) {
                System.out.println("address: " + rs.getString("address") +
                        ", Average marks: " + rs.getDouble("average_marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}