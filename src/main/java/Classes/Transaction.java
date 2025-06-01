/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.Timestamp;

/**
 *
 * @author cagla
 */
//I am create a object transaction for transaction management process
public class Transaction {

    public Transaction(int id, Timestamp date, String addedBy, String type, String category, double amount, String currency, String description) {
        this.id = id;
        this.date = date;
        this.addedBy = addedBy;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private Timestamp date;
    private String addedBy;
    private String type;
    private String category;
    private double amount;
    private String currency;
    private String description;

    // Constructor, getter ve setter metodları
}
