/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.achs.jdbc.example.maven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Aakash
 */
public class BlobExample {
    
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_information_system",
                    "root", "");
            String sql = "INSERT into employee (full_name, address, age, contact_number, image) VALUES (?, ?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, "Aakash Shakya");
            statement.setString(2, "Lagan tole");
            statement.setInt(3, 30);
            statement.setString(4, "9851320011");
            FileInputStream fin = new FileInputStream("C:\\Users\\hp\\Documents\\Advance Java Questions.docx");
            statement.setBinaryStream(5, fin);
            int isInserted = statement.executeUpdate();
            if (isInserted == 1) {
                System.out.println("Record added successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("The given Driver class could not be loaded.");
        } catch (SQLException se) {
            System.out.println("Exception occurred when carrying out SQL operation. " + se.getMessage());
        }  catch (FileNotFoundException fne) {
            System.out.println("File not found exception. " + fne.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
}
