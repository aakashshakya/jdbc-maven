/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.achs.jdbc.example.maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aakash
 */
public class JdbcExample {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/students",
                            "root", "");
            String sql = "Select * from user";
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                System.out.println("The student full name is " + result.getString("full_name"));
                System.out.println("The student address is " + result.getString("address"));
                System.out.println("The student mobile number is " + result.getString("mobile_number"));
                System.out.println("The student college is " + result.getString("college"));
                System.out.println("The student faculty is " + result.getString("faculty"));
                System.out.println("========================================");
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) {
                System.out.println("Connection closed");
                conn.close();
            }
        }

    }
}
