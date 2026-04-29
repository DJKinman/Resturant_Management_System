package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FoodOrder implements FileLists{
    private int id;
    private ArrayList<FoodItem> order;
    private  double total;
    static public ArrayList<FoodOrder> allOrders;

    static {
        allOrders = new ArrayList<>();

        FoodOrder temp = new FoodOrder();
        try {
            temp.getFromFile(new File("CompletedOrders.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor for the FoodOrder class
     */
    FoodOrder(){
        if (!allOrders.isEmpty()){
            id = allOrders.getLast().getId() + 1;
        } else {
            id = allOrders.size() + 1;
        }

        order = new ArrayList<>();
        total = 0.0;
    }

    /**
     * Private constructor can only be used inside the FoodOrder class
     * for use in getting orders from file
     * @param fromFile if the order comes from the file
     */
    private FoodOrder(boolean fromFile){
        id = 0;
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

    public void addToAllOrders() throws FileNotFoundException {
        allOrders.add(this);

        for (FoodOrder o : allOrders){
            System.out.println("Order " + o.getId() + " total: " + o.getTotal() + " items: " + o.getOrder().size());
        }

        writeToFile(new File("CompletedOrders.txt"));
    }

    /**
     * Returns the total of the order
     * @return total the total of the order
     */
    public double getTotal(){
        return total;
    }

    /**
     * Returns the ID of the order
     * @return id the ID of the order
     */
    public int getId(){
        return id;
    }

    /**
     * Sets the orders ID
     * @param id teh ID of the order
     */
    public void setID(int id){
        this.id = id;
    }

    /**
     * Returns the order
     * @return order the order
     */
    public ArrayList<FoodItem> getOrder(){
        return order;
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

    @Override
    public void getFromFile(File file) throws FileNotFoundException {
        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()){
            String line = readFile.nextLine();

            //splits each line into diffrent parts, thanks Yash for telling us about this
            String[] lineParts = line.split(",");

            String orderID = lineParts[0];
            String orderTotal = lineParts[1];

            FoodOrder currentOrder = new FoodOrder(true);
            currentOrder.setID(Integer.parseInt(orderID));

            //for every item in the reordered order creates a new string builder
            for (int i = 2; i < lineParts.length; i++){
                String item = lineParts[i];

                //checks each item against allItems to see which it matches to inorder to add
                for (FoodItem foodItem : Menu.allItems){
                    if (foodItem.getName().trim().equals(item.trim())){
                        currentOrder.addFoodItem(foodItem);
                    }
                }
            }

            allOrders.add(currentOrder);

        }
    }

    @Override
    public void writeToFile(File file) throws FileNotFoundException {
        PrintWriter writeOutput = new PrintWriter(file);

        //prints each user in the ArrayList to the file, overwriting it
        for (FoodOrder order : allOrders){
            StringBuilder line = new StringBuilder();
            line.append(order.getId() + "," + order.getTotal());

            for (FoodItem item : order.getOrder()){
                line.append("," + item.getName());
            }

            writeOutput.println(line);
        }

        writeOutput.close();
    }
}
