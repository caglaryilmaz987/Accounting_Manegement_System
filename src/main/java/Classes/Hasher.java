/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author cagla
 */
public class Hasher {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/users"; // my database address (localhost and database name: users)
        String user = "root"; // my username
        String dbPassword = "root"; // my database password

        try {
            // Loading the JDBC driver, required to establish the connection
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Opening the database connection
            Connection con = DriverManager.getConnection(url, user, dbPassword);

            // Fetching all users
            String selectSql = "SELECT username, password FROM user"; // I will fetch the username and password columns from the user table
            Statement stmt = con.createStatement(); // object to execute the query
            ResultSet rs = stmt.executeQuery(selectSql); // executing the query and retrieving the results

            // iterating through users in the ResultSet
            while (rs.next()) {
                String username = rs.getString("username"); // getting the username
                String plainPassword = rs.getString("password"); // getting the password in plain text

                // Checking if the password is already hashed (bcrypt hashes start with $2a$)
                if (!plainPassword.startsWith("$2a$")) {
                    // If not hashed, hashing the password using bcrypt
                    String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

                    // Update query: replacing the user's password with the hashed one
                    String updateSql = "UPDATE user SET password = ? WHERE username = ?";
                    PreparedStatement updatePst = con.prepareStatement(updateSql); // preparing the update query
                    updatePst.setString(1, hashedPassword); // setting the hashed password for the first placeholder
                    updatePst.setString(2, username); // setting the username for the second placeholder
                    updatePst.executeUpdate(); // executing the update query

                    System.out.println("Updated password for user: " + username); // info message
                }
            }

            // Closing everything
            rs.close();
            stmt.close();
            con.close();
            System.out.println("All passwords updated"); // process completed

        } catch (Exception e) {
            e.printStackTrace(); // printing details if an error occurs
        }

    }
}
