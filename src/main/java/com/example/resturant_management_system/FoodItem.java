package com.example.resturant_management_system;

public class FoodItem {
    private String name;
    private double price;
    private String modifications;

    FoodItem(String name, double price){
        this.name = name;
        this.price = price;
    }

    //adds a modification to the item
    public void addModification(String modification){
        //appends the modification to the order with a tab in front of it to denote that it is not a new order
        modifications += "\t" + modification + "\n";
    }

    //returns the name of the item
    public String getName(){
        return name;
    }

    //returns the price of the item
    public double getPrice(){
        return price;
    }

    //returns the modifications of the item
    //mainly for the recipet
    public String getModifications(){
        return modifications;
    }

    @Override
    public String toString() {
        String result = name + "\t\t" + price
                + "\n" + modifications;

        return result;
        

    }
}
