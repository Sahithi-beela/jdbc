package com.example.JDBC.jdbcpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertSale {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Input Sale ID (rollno)
            System.out.print("Enter Sale ID (rollno): ");
            int rollno = 0;
            while (true) {
                try {
                    rollno = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for Sale ID. Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input
                }
            }

            // Input Item Name (name)
            System.out.print("Enter Item Name (name): ");
            String itemName = scanner.nextLine();

            // Input Quantity
            System.out.print("Enter Quantity: ");
            int quantity = 0;
            while (true) {
                try {
                    quantity = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for Quantity. Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input
                }
            }

            // Input Price per Item
            System.out.print("Enter Price per Item: ");
            double price = 0;
            while (true) {
                try {
                    price = scanner.nextDouble();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for Price. Please enter a valid number.");
                    scanner.next(); // Clear the invalid input
                }
            }

            // Calculate total amount (quantity * price)
            double totalAmount = quantity * price;

            // Insert into the 'students' table
            String url = "jdbc:sqlserver://localhost:1433;databaseName=test;encrypt=true;trustServerCertificate=true;";
            try (Connection con = DriverManager.getConnection(url, "SA", "Sahithi@23");
                 PreparedStatement pstmt = con.prepareStatement("INSERT INTO students (rollno, name, marks) VALUES (?, ?, ?)")) {

                pstmt.setInt(1, rollno); // Sale ID stored as rollno
                pstmt.setString(2, itemName); // Item name stored as name
                pstmt.setDouble(3, totalAmount); // Total amount stored as total_marks

                int rowsInserted = pstmt.executeUpdate();
                System.out.println(rowsInserted + " sale transaction inserted into students table.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

