package com.shahab12344.expensetracker;

public class model {

    String type;
    String date;
    double amount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public model(String type, String date, double amount) {
        this.type = type;
        this.date = date;
        this.amount = amount;
    }
}
