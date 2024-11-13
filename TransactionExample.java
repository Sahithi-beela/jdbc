package com.example.JDBC.jdbcpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TransactionExample {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=test;encrypt=true;trustServerCertificate=true;";
        try (Connection con = DriverManager.getConnection(url, "SA", "Sahithi@23");
             Scanner scanner = new Scanner(System.in)) {

            // Get input for the first student
            System.out.print("Enter first Student Roll Number to update: ");
            int rollno1 = scanner.nextInt();
            System.out.print("Enter new Total Marks for first Student: ");
            double newMarks1 = scanner.nextDouble();

            // Get input for the second student
            System.out.print("Enter second Student Roll Number to update: ");
            int rollno2 = scanner.nextInt();
            System.out.print("Enter new Total Marks for second Student: ");
            double newMarks2 = scanner.nextDouble();

            // Start the transaction
            con.setAutoCommit(false); // Disable auto-commit for transaction control

            try (PreparedStatement pstmt1 = con.prepareStatement("UPDATE students SET marks = ? WHERE rollno = ?");
                 PreparedStatement pstmt2 = con.prepareStatement("UPDATE students SET marks = ? WHERE rollno = ?")) {

                // Update total marks for the first student
                pstmt1.setDouble(1, newMarks1);
                pstmt1.setInt(2, rollno1);
                pstmt1.executeUpdate();

                // Update total marks for the second student
                pstmt2.setDouble(1, newMarks2);
                pstmt2.setInt(2, rollno2);
                pstmt2.executeUpdate();

                // Commit the transaction if both updates are successful
                con.commit();
                System.out.println("Transaction committed successfully. Both records updated.");

            } catch (SQLException e) {
                // Roll back the transaction if any update fails
                con.rollback();
                System.out.println("Transaction failed, rolling back changes.");
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
