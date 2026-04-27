package com.example.resturant_management_system;

import java.util.ArrayList;

public class FoodOrder {
    private ArrayList<FoodItem> order;
    private  double total;

    FoodOrder(){
        order = new ArrayList<>();
        total = 0.0;
    }

    //adds the item to the order
    public void addFoodItem(FoodItem item){
        order.add(item);
        total += item.getPrice();
    }

    public void removeFoodItem(FoodItem item){
        order.remove(item);
        total -= item.getPrice();
    }

    //adds a modification to the last item in the order
    private void addModification(String modification){
        int lastIndex = order.size() - 1;
        order.get(lastIndex).addModification(modification);
    }

    public void clearOrder(){
        order.clear();
        total = 0.0;
    }

    public double getTotal(){
        return total;
    }

    //prints the receipt of the order
    @Override
    public String toString() {
        String result = "";

        for (FoodItem item : order){
            result += item;
        }

        return result;
    }
}
