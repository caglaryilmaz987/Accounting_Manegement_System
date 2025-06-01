/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.accaunting_manegment_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cagla
 */
// I create a genaric class 
public abstract class Manager<T> {

    protected Connection conn;

    // Constructor establishes the database connection
    public Manager() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "root");
    }

    // Abstract method to load data into the provided table model (generic)
    public abstract void loadData(DefaultTableModel model);

    // Abstract method to delete a record by ID (generic)
    public abstract void delete(int id);

    // Abstract method to update a record with a generic object
    public abstract void update(T obj);

    // Method to close the database connection safely
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
