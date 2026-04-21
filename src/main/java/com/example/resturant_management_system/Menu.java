package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Menu implements FileLists{
    private static ArrayList<FoodItem> allItems;
    //THESE ARE TEMP NAMES!!!! REPLACE LATER!!!!!
    private static ArrayList<FoodItem> menu1;
    private static ArrayList<FoodItem> menu2;
    private static ArrayList<FoodItem> menu3;

    static{
        allItems = new ArrayList<>();

        //AGAIN THESE ARE TEMP NAME!!! REMEMBER TO REPLACE!!!
        menu1 = new ArrayList<>();
        menu2 = new ArrayList<>();
        menu3 = new ArrayList<>();

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
        for (FoodItem item : allItems){
            if (item.getMenu().equals("Menu 1")){
                menu1.add(item);
            } else if (item.getMenu().equals("Menu 2")) {
                menu2.add(item);
            } else if (item.getMenu().equals("Menu 3")){
                menu3.add(item);
            }
        }
    }

    public static ArrayList<FoodItem> getMenu1() {
        return menu1;
    }

    public static ArrayList<FoodItem> getMenu2() {
        return menu2;
    }

    public static ArrayList<FoodItem> getMenu3() {
        return menu3;
    }

//    @Override
//    public FoodItem createArrayObj(String line) {
//        StringBuilder menuIndicator = new StringBuilder();
//        StringBuilder name = new StringBuilder();
//        StringBuilder price = new StringBuilder();
//
//        //checks to see if comma seperating the email and password was passed or not
//        Boolean passedMenuIndicator = false;
//        Boolean passedSeperator = false;
//
//        //loops through the line character by character
//        for (int i = 0; i < line.length(); i ++){
//
//            if (!passedMenuIndicator) {
//                //checks if current character is the separator
//                if (line.charAt(i) == ',') {
//                    passedSeperator = true;
//                } else {    //if not the separator, adds the current chapter to the menuIndicator string
//                    menuIndicator.append(line.charAt(i));
//                }
//            } else if (!passedSeperator){     //if not passed the separator
//                //checks if current character is the separator
//                if (line.charAt(i) == ','){
//                    passedSeperator = true;
//                } else {    //if not the separator, adds the current chapter to the name string
//                    name.append(line.charAt(i));
//                }
//            } else {    //if passed the separator, add teh current character to the price string
//                price.append(line.charAt(i));
//            }
//        }
//
//        //converts string from file to double
//        double priceNum = Double.parseDouble(String.valueOf(price));
//
//        //creates and returns a FoodItem
//        FoodItem foodItem = new FoodItem(name.toString(), priceNum, menuIndicator.toString());
//        return foodItem;
//    }

    @Override
    public void getFromFile(File file) throws FileNotFoundException {

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
