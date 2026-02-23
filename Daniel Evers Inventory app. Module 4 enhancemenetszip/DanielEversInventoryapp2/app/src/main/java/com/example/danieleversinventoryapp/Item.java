package com.example.danieleversinventoryapp;

public class Item {

    private int id;
    private String name;
    private int quantity;
    private String condition;
    private boolean willingToTrade;
    private double price;

    public Item(int id, String name, int quantity, String condition, boolean willingToTrade, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.condition = condition;
        this.willingToTrade = willingToTrade;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCondition() {
        return condition;
    }

    public boolean isWillingToTrade() {
        return willingToTrade;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setWillingToTrade(boolean willingToTrade) {
        this.willingToTrade = willingToTrade;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}