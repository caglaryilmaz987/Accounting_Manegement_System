/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.accaunting_manegment_system;

import Classes.Transaction;
import Classes.Transaction;
import com.mycompany.accaunting_manegment_system.Login_Screen;
import com.mycompany.accaunting_manegment_system.Login_Screen;
import com.mycompany.accaunting_manegment_system.Login_Screen;
import com.mycompany.accaunting_manegment_system.Login_Screen;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cagla
 */
//I derived it from the genaric class, it has a dynamic value scale
public class Transaction_Maneger extends Manager<Transaction> {

    public Transaction_Maneger() throws SQLException {
        super();
    }

    @Override
    public void loadData(DefaultTableModel model) {
        model.setRowCount(0);

        // I got the logged-in user's info from the Login screen
        String currentUsername = Login_Screen.username;  // The username of the logged-in user
        String currentUserRole = Login_Screen.usertype;  // The role of the logged-in user ("admin" or "user")

        // I just declared the SQL query here to prepare it based on the role
        String sql;

        // If the user is admin, query to fetch all records so admin can see all transactions
        if ("admin".equals(currentUserRole)) {
            sql = "SELECT * FROM transactions";
        } // If the user is a normal user, they will only see transactions they added
        else {
            sql = "SELECT * FROM transactions WHERE added_by = ?";
        }

        try (
                // Preparing the SQL statement
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // If user is not admin, set the username parameter to only show user's own data
            if (!"admin".equals(currentUserRole)) {
                pstmt.setString(1, currentUsername); // Replace WHERE added_by = ? with username
            }

            // Execute query and get the result set
            ResultSet rs = pstmt.executeQuery();

            // Add each row from the result set into the table model one by one
            while (rs.next()) {
                int id = rs.getInt("idTransactions");              // Transaction ID
                Timestamp date = rs.getTimestamp("date");          // Date
                String enteredBy = rs.getString("added_by");       // Person who added it
                String type = rs.getString("type");                 // Income/Expense type
                String category = rs.getString("category");         // Category
                double amount = rs.getDouble("amount");             // Amount
                String currency = rs.getString("currency");         // Currency
                String description = rs.getString("description");   // Description

                // Add the row to the table
                model.addRow(new Object[]{id, date, enteredBy, type, category, amount, currency, description});
            }

        } catch (SQLException e) {
            // Print error to console and alert the user if an error occurs
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Data couldn't be loaded.");
        }
    }

    @Override
    public void delete(int transactionId) {
        // Here I wrote this method to delete the selected transaction by taking an id parameter
        String query = "DELETE FROM transactions WHERE idTransactions = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Put the id into the query
            pstmt.setInt(1, transactionId);
            // Execute the delete query
            pstmt.executeUpdate();
            // If no error occurs, show success message
            JOptionPane.showMessageDialog(null, "Transaction successfully deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Delete process failed.");
        }
    }

    @Override
    public void update(Transaction transaction) {
        // Here I updated the database according to the information coming from the object
        String query = "UPDATE transactions SET type = ?, category = ?, amount = ?, currency = ?, description = ? WHERE idTransactions = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, transaction.getType());
            pstmt.setString(2, transaction.getCategory());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getCurrency());
            pstmt.setString(5, transaction.getDescription());
            pstmt.setInt(6, transaction.getId());
            // Execute the update query
            int rowsAffected = pstmt.executeUpdate();
            // If the query runs successfully, show message
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Transaction successfully updated.");
            } else {
                JOptionPane.showMessageDialog(null, "No transaction found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Update process failed.");
        }
    }
}
