package com.example.resturant_management_system;

public class FoodItem {
    private String name;
    private double price;
    private String menu;

    /**
     * Constructor for the FoodItem class
     * @param name the name of the FoodItem
     * @param price the price of the FoodItem
     * @param menu the menu the FoodItem belongs to
     */
    FoodItem(String name, double price, String menu){
        this.name = name;
        this.price = price;
        this.menu = menu;
    }

    /**
     * Returns the name of the item
     * @return name the name of the FoodItem
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the price of the item
     * @return price the price of the FoodItem
     */
    public double getPrice(){
        return price;
    }

    /**
     * Returns what menu the FoodItem belongs to
     * @return menu what menu the FoodItem belongs to
     */
    public String getMenu(){
        return menu;
    }

    /**
     * How the FoodItem will be printed
     * @return how the FoodItem will be printed
     */
    @Override
    public String toString() {
        String result = name + "\t\t$" + price + "\n";

        return result;
    }
}
