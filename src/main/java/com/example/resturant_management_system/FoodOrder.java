package com.example.resturant_management_system;

import java.util.ArrayList;

public class FoodOrder {
    private ArrayList<FoodItem> order;
    private  double total;

    /**
     * Constructor for the FoodOrder class
     */
    FoodOrder(){
        order = new ArrayList<>();
        total = 0.0;
    }

    /**
     * Adds the item to the order
     * @param item the FoodItem to be added to the order
     */
    public void addFoodItem(FoodItem item){
        order.add(item);
        total += item.getPrice();
    }

    /**
     * Removes an item from the order
     * @param item the FoodItem to remove from the order
     */
    public void removeFoodItem(FoodItem item){
        order.remove(item);
        total -= item.getPrice();
    }

    /**
     * Clears the order of all items and resets the price
     */
    public void clearOrder(){
        order.clear();
        total = 0.0;
    }

    /**
     * Returns the total of the order
     * @return total the total of the order
     */
    public double getTotal(){
        return total;
    }

    /**
     * Prints the receipt of the order
     * @return the text of the entire order
     */
    @Override
    public String toString() {
        String result = "";

        for (FoodItem item : order){
            result += item;
        }

        return result;
    }
}
