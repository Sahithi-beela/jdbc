package com.example.JDBC.jdbcpractice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
public class UpdatestudentsName {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter students ID to update: ");
            int prodId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new students Name: ");
            String newName = scanner.nextLine();
            String url = "jdbc:sqlserver://localhost:1433;databaseName=test;encrypt=true;trustServerCertificate=true;";
            try (Connection con = DriverManager.getConnection(url, "SA", "Sahithi@23");
                 PreparedStatement pstmt = con.prepareStatement("UPDATE students SET name = ? WHERE rollno = ?")) {
                pstmt.setString(1, newName);
                pstmt.setInt(2, prodId);
                int rowsUpdated = pstmt.executeUpdate();
                System.out.println(rowsUpdated + " row(s) updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}