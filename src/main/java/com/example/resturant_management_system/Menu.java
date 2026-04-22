package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu implements FileLists{
    private static ArrayList<FoodItem> allItems;
    //THESE ARE TEMP NAMES!!!! REPLACE LATER!!!!!
    private static ArrayList<FoodItem> drinks;
    private static ArrayList<FoodItem> kids;
    private static ArrayList<FoodItem> general;

    static{
        allItems = new ArrayList<>();

        //AGAIN THESE ARE TEMP NAME!!! REMEMBER TO REPLACE!!!
        drinks = new ArrayList<>();
        kids = new ArrayList<>();
        general = new ArrayList<>();

        //have to create a Menu object to call non-static methods getFromFIle() nad seperateMenus()
        //https://stackoverflow.com/questions/2042813/calling-non-static-method-in-static-method-in-java
        Menu menu = new Menu();

        try {
            menu.getFromFile(new File("MenuItems.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        menu.seperateMenus();


    }


    public Menu() {

    }

    //checks what menu each FoodItem belongs to and sorts it into the corrent menu
    //IMPORTNAT!!!! YET AGAIN THESE ARE TEMPORARY NAMES!!!! REPLACE LATER!!!! DO NOT FORGET!!!!!
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

    public static ArrayList<FoodItem> getDrinksMenu() {
        return drinks;
    }

    public static ArrayList<FoodItem> getKidsMenu() {
        return kids;
    }

    public static ArrayList<FoodItem> getMenu3() {
        return general;
    }

    @Override
    public void getFromFile(File file) throws FileNotFoundException {
        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()){
            String line = readFile.nextLine();

            StringBuilder itemName = new StringBuilder();
            StringBuilder itemPrice = new StringBuilder();
            StringBuilder itemMenu = new StringBuilder();

            Boolean nameSeperatorPassed = false;
            Boolean priceSeperatorPassed = false;

            for (int i = 0; i < line.length(); i ++){

                //gets the item name from file
                while (nameSeperatorPassed == false){
                    if (line.charAt(i) == ','){
                        nameSeperatorPassed = true;
                    } else {
                        itemName.append(line.charAt(i));
                    }
                }

                //gets the item price from file
                while (priceSeperatorPassed == false){
                    if (line.charAt(i) == ','){
                        priceSeperatorPassed = true;
                    } else {
                        itemPrice.append(line.charAt(i));
                    }
                }

                //gets the item menu from file
                if (line.charAt(i) == ','){
                    break;
                } else {
                    itemMenu.append(line.charAt(i));
                }

            }

        }
    }

    @Override
    public void writeToFile(File file) throws FileNotFoundException {
        PrintWriter writeOutput = new PrintWriter(file);

        //prints each FoodItem in the ArrayList to the file, overwriting it
        for (FoodItem item : allItems){
            writeOutput.println(item);
        }

        writeOutput.close();
    }
}
