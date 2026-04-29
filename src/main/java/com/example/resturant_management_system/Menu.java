package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements FileLists{
    public static ArrayList<FoodItem> allItems;
    private static ArrayList<FoodItem> drinks;
    private static ArrayList<FoodItem> kids;
    private static ArrayList<FoodItem> general;

    //static blocks are called when the class file is first loaded into memory
    static{
        //initalizes theArrayLists
        allItems = new ArrayList<>();
        drinks = new ArrayList<>();
        kids = new ArrayList<>();
        general = new ArrayList<>();

        //have to create a Menu object to call non-static methods getFromFIle() nad seperateMenus()
        //https://stackoverflow.com/questions/2042813/calling-non-static-method-in-static-method-in-java
        Menu menu = new Menu();

        //gets the menu items from the file
        try {
            menu.getFromFile(new File("MenuItems.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //separates all the menu items into their proper menus
        menu.seperateMenus();
    }

    public Menu() {

    }

    /**
     * Separates all the FoodItems into the correct menus
     */
    public void seperateMenus(){
        for (FoodItem item: allItems){
            if (item.getMenu().equals("drinks")){
                drinks.add(item);
            } else if (item.getMenu().equals("kids")) {
                kids.add(item);
            } else if (item.getMenu().equals("general")) {
                general.add(item);
            }
        }
    }

    /**
     * Returns the Drinks menu
     * @return  drinksMenu  the Drinks menu
     */
    public static ArrayList<FoodItem> getDrinksMenu() {
        return drinks;
    }

    /**
     * Returns the Kids menu
     * @return  kidsMenu  the kids menu
     */
    public static ArrayList<FoodItem> getKidsMenu() {
        return kids;
    }

    /**
     * Returns the General menu
     * @return  generalMenu  the General menu
     */
    public static ArrayList<FoodItem> getGeneralMenu() {
        return general;
    }

    /**
     * Gets all the menu items from the MenuItems.txt file
     * @param file the file that holds all the menu items
     * @throws FileNotFoundException when the file that holds all the menu items is not found
     */
    @Override
    public void getFromFile(File file) throws FileNotFoundException {
        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()){
            String line = readFile.nextLine();

            //splits each line into diffrent parts, thanks Yash for telling us about this
            String[] lineParts = line.split(",");

            String itemName = lineParts[0];
            String itemPrice = lineParts[1];
            String itemMenu = lineParts[2];

            allItems.add(new FoodItem(itemName, Double.parseDouble(itemPrice), itemMenu));

        }
    }

    /**
     * Writes all the menu items to the MenuItems.txt file
     * @param file the file that holds all the menu items
     * @throws FileNotFoundException when the file that hodls all the menu items is not found
     */
    @Override
    public void writeToFile(File file) throws FileNotFoundException {
        //this method should not run inside Menu.java because the user should not have a way to edit the menu from inside the program
        PrintWriter writeOutput = new PrintWriter(file);

        //prints each FoodItem in the ArrayList to the file, overwriting it
        for (FoodItem item : allItems){
            writeOutput.println(item);
        }

        writeOutput.close();
    }
}
