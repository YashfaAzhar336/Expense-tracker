package com.shahab12344.expensetracker;

public class Income_model {
    private String type;
    private String date;
    private double amount;

    public Income_model(String type, String date, double amount) {
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
