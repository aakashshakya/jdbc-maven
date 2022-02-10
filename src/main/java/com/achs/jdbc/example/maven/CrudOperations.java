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
import java.util.Scanner;

/**
 *
 * @author Aakash
 */
public class CrudOperations {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Welcome to the student CRUD operation program.");

        Scanner scanner = new Scanner(System.in);
        String request = "";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = null;
        Statement statement = null;
        String sql;

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/students",
                    "root",
                    "");
            statement = conn.createStatement();
            String fullName, address, mobileNumber, college, faculty;
            while (!request.equals("exit")) {
                System.out.println("What operation do you want to do?");
                System.out.println("Type \"read\" to show all student data.");
                System.out.println("Type \"insert\" to inser new student data.");
                System.out.println("Type \"update\" to update existing student data.");
                System.out.println("Type \"delete\" to delete student data.");
                System.out.println("Type \"exit\" to exit the program.");
                request = scanner.nextLine();
                if(!request.equals("exit")) {
                    switch (request.toLowerCase()){
                        case "read":
                            sql = "Select * from users";
                            ResultSet result = statement.executeQuery(sql);
                            while(result.next()) {
                                System.out.println("The row id is " + result.getString("id"));
                                System.out.println("The student full name is " + result.getString("full_name"));
                                System.out.println("The student address is " + result.getString("address"));
                                System.out.println("The student mobile number is " + result.getString("mobile_number"));
                                System.out.println("The student college is " + result.getString("college"));
                                System.out.println("The student faculty is " + result.getString("faculty"));
                                System.out.println("========================================");
                            }
                            break;
                        case "insert":
                            System.out.println("Insert request!!");
                            System.out.println("Enter full name:");
                            fullName = scanner.nextLine();
                            System.out.println("Enter address:");
                            address = scanner.nextLine();
                            System.out.println("Enter mobile number:");
                            mobileNumber = scanner.nextLine();
                            System.out.println("Enter college:");
                            college = scanner.nextLine();
                            System.out.println("Enter faculty:");
                            faculty = scanner.nextLine();
                            sql = "INSERT INTO users (full_name, address, mobile_number, college, faculty)"
                                    + " VALUES ('" + fullName + "',"
                                    + "'" + address + "', '" + mobileNumber + "',"
                                    + "'" + college + "', '" + faculty + "' )";
                            
                            int value = statement.executeUpdate(sql);
                            if(value == 1) {
                                System.out.println("Record added successfully.");
                            }
                            break;
                        case "update":
                            System.out.println("Update request!!");
                            System.out.println("Enter the row id whose information you want to update:");
                            int id = Integer.parseInt(scanner.nextLine());
                            
                            if(id > 0) {
                                sql = "Select * from users where id = " + id;
                                result = statement.executeQuery(sql);
                                if(result.next()) {
                                    do {
                                        fullName = result.getString("full_name");
                                        address = result.getString("address");
                                        mobileNumber = result.getString("mobile_number");
                                        college = result.getString("college");
                                        faculty = result.getString("faculty");
                                    } while(result.next());
                                    
                                    String tempFullName, tempAddress, tempMobileNumber, tempCollege, tempFaculty;
                                    System.out.println("Enter full name:");
                                    tempFullName = scanner.nextLine();
                                    System.out.println("Enter address:");
                                    tempAddress = scanner.nextLine();
                                    System.out.println("Enter mobile number:");
                                    tempMobileNumber = scanner.nextLine();
                                    System.out.println("Enter college:");
                                    tempCollege = scanner.nextLine();
                                    System.out.println("Enter faculty:");
                                    tempFaculty = scanner.nextLine();
                                    
                                    if(tempFullName.equals("")) {
                                        tempFullName = fullName;
                                    }
                                    
                                    if(tempAddress.equals("")) {
                                        tempAddress = address;
                                    }
                                    
                                    if(tempMobileNumber.equals("")) {
                                        tempMobileNumber = mobileNumber;
                                    }
                                    
                                    if(tempCollege.equals("")) {
                                        tempCollege = college;
                                    }
                                    
                                    if(tempFaculty.equals("")) {
                                        tempFaculty = faculty;
                                    }
                                    
                                    sql = "UPDATE users SET full_name='"+tempFullName+"', address='"+tempAddress+"', "
                                        + "mobile_number = '"+tempMobileNumber+"', college = '"+tempCollege+"', "
                                        + "faculty = '"+tempFaculty+"' WHERE id = " + id;

                                    value = statement.executeUpdate(sql);
                                    if(value == 1){
                                        System.out.println("Record updated successfully.");
                                    } else {
                                        System.out.println("Couldn't update the record.");
                                    }
                                } else {
                                    System.out.println("The data in the row " + id + " does not exist.");
                                }
                            } else {
                                System.out.println("Invalid ID.");
                            }
                            break;
                        case "delete":
                            System.out.println("Delete request!!");
                            System.out.println("Please enter the row id to delete the information.");
                            id = Integer.parseInt(scanner.nextLine());
                            
                            if(id > 0) {
                                sql = "Select * from users where id = " + id;
                                result = statement.executeQuery(sql);
                                if(result.next()) {
                                    sql = "DELETE FROM users where id = " + id ;
                                    value = statement.executeUpdate(sql);
                                    if(value == 1) {
                                        System.out.println("Record deleted successfully.");
                                    } else {
                                        System.out.println("Unable to delete the data.");
                                    }
                                } else {
                                    System.out.println("Invalid row id.");
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid input. Please try again.");
                            break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if(conn != null) {
                System.out.println("Connection closed.");
                conn.close();
            }
        }
    }

}
