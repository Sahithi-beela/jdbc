package com.example.JDBC.jdbcpractice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
public class CreateTables {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=test;encrypt=true;trustServerCertificate=true;";
        String user = "SA";
        String password = "Sahithi@23";
        String insertstudents1 = "INSERT INTO students(rollno, name, address) VALUES (4, 'vasu', 'vizag')";
        String insertstudents2 = "INSERT INTO students(rollno, name, address) VALUES (5, 'sahithi', 'vzm')";
        String insertstudents3 = "INSERT INTO students(rollno, name, address) VALUES (6, 'bhargavi', 'adpm')";
        String insertstudents4 = "INSERT INTO students(rollno, name, address) VALUES (7, 'komali', 'ynd')";
        String insertstudents5 = "INSERT INTO students(rollno, name, address) VALUES (8, 'nivi', 'ynd')";
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(insertstudents1);
            stmt.executeUpdate(insertstudents2);
            stmt.executeUpdate(insertstudents3);
            stmt.executeUpdate(insertstudents4);
            stmt.executeUpdate(insertstudents5);
            System.out.println("Sample data inserted into 'students' table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
