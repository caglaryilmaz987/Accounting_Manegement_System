/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cagla
 */
public class connection {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/users"; // Database URL and database name "users"
        String username = "root"; // Database username
        String password = "root"; // Database password

        try {
            // Loading the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establishing connection to the database
            Connection con = DriverManager.getConnection(url, username, password);
            // If connection is successful, print a message
            System.out.println("Connection is successful.");

            // Closing the connection
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            // Print the error details if connection fails
            e.printStackTrace();
        }
    }

}
