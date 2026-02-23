package com.example.danieleversinventoryapp;

public class Item {
    int id;
    String name;
    int quantity;

    private String condition;
    private boolean willingToTrade;

    public Item(int id, String name, int quantity, String condition, boolean willingToTrade) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.condition = condition;
        this.willingToTrade = willingToTrade;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCondition (String condition) {
        this.condition = condition;
    }

    public void setWillingToTrade(boolean willingToTrade) {
        this.willingToTrade = willingToTrade;
    }
}
