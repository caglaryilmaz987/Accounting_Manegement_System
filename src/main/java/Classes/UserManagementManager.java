/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author cagla
 */
public class UserManagementManager {

    private final String url = "jdbc:mysql://localhost:3306/users"; // Database URL
    private final String user = "root"; // Database username
    private final String password = "root"; // Database password

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

// Method to get all users, returns a List<User>
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>(); // Created an ArrayList to store users
        // SQL query to select all from user table
        String sql = "SELECT * FROM user";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            // Connected to DB, prepared statement and executed query
            while (rs.next()) {
                // Iterate through result set rows

                // Created a new User object for each row
                User user = new User(
                        // Retrieved the data in order
                        rs.getInt("ID_NUMBER"),
                        rs.getString("UserName"),
                        rs.getString("Password"),
                        rs.getString("Role")
                );
                // Added user to the list
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occurred while fetching users.\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        // Return the list of users
        return users;
    }

// Add a new user
    public boolean addUser(String username, String password, String role) {
        // Username validation: at least 4 characters, only letters and digits
        if (!username.matches("^[a-zA-Z0-9]{4,}$")) {
            JOptionPane.showMessageDialog(null,
                    "Username must be at least 4 characters long and contain only letters and digits.",
                    "Invalid Username",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Password validations
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null,
                    "Password must be at least 8 characters long.",
                    "Invalid Password",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(null,
                    "Password must contain at least one uppercase letter.",
                    "Invalid Password",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!password.matches(".*[0-9].*")) {
            JOptionPane.showMessageDialog(null,
                    "Password must contain at least one digit.",
                    "Invalid Password",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            JOptionPane.showMessageDialog(null,
                    "Password must contain at least one special character.",
                    "Invalid Password",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Check if the username already exists in the user table
        String checkSql = "SELECT * FROM user WHERE UserName = ?";
        try (Connection conn = connect(); PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, username);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null,
                            "This username already exists. Please choose another.",
                            "Duplicate Username",
                            JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error while checking existing username.\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Insert query with updated column names
        String sql = "INSERT INTO user (UserName, Password, Role) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occurred while adding the user.\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

// Update user information
    public boolean updateUser(int id, String username, String password, String role) {
        // Update query with updated column names
        String sql = "UPDATE user SET UserName = ?, Password = ?, Role = ? WHERE ID_NUMBER = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.setInt(4, id);
            int updatedRows = pstmt.executeUpdate();
            return updatedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occurred while updating the user.\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

// Delete user by ID
    public boolean deleteUser(int id) {
        // Delete query with updated column name
        String sql = "DELETE FROM user WHERE ID_NUMBER = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int deletedRows = pstmt.executeUpdate();
            return deletedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occurred while deleting the user.\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
