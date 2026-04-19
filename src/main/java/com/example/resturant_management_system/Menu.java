package com.example.resturant_management_system;

import java.util.ArrayList;

public class Menu extends FileLists<FoodItem>{
    //THESE ARE TEMP NAMES!!!! REPLACE LATER!!!!!
    private ArrayList<FoodItem> menu1;
    private ArrayList<FoodItem> menu2;
    private ArrayList<FoodItem> menu3;


    public Menu() {
        list = new ArrayList<>();

        //REMINDER THESE ARE TEMP NAMES, REPLACE LATER!!!!!
        menu1 = new ArrayList<>();
        menu2 = new ArrayList<>();
        menu3 = new ArrayList<>();
    }

    //checks what menu each FoodItem belongs to and sorts it into the corrent menu
    //IMPORTNAT!!!! YET AGAIN THESE ARE TEMPORARY NAMES!!!! REPLACE LATER!!!! DO NOT FORGET!!!!!
    public void seperateMenus(){
        for (FoodItem item : list){
            if (item.getMenu().equals("Menu 1")){
                menu1.add(item);
            } else if (item.getMenu().equals("Menu 2")) {
                menu2.add(item);
            } else if (item.getMenu().equals("Menu 3")){
                menu3.add(item);
            }
        }
    }

    public ArrayList<FoodItem> getMenu1() {
        return menu1;
    }


    public ArrayList<FoodItem> getMenu2() {
        return menu2;
    }

    public ArrayList<FoodItem> getMenu3() {
        return menu3;
    }

    @Override
    public FoodItem createArrayObj(String line) {
        StringBuilder menuIndicator = new StringBuilder();
        StringBuilder name = new StringBuilder();
        StringBuilder price = new StringBuilder();

        //checks to see if comma seperating the email and password was passed or not
        Boolean passedMenuIndicator = false;
        Boolean passedSeperator = false;

        //loops through the line character by character
        for (int i = 0; i < line.length(); i ++){

            if (!passedMenuIndicator) {
                //checks if current character is the separator
                if (line.charAt(i) == ',') {
                    passedSeperator = true;
                } else {    //if not the separator, adds the current chapter to the menuIndicator string
                    menuIndicator.append(line.charAt(i));
                }
            } else if (!passedSeperator){     //if not passed the separator
                //checks if current character is the separator
                if (line.charAt(i) == ','){
                    passedSeperator = true;
                } else {    //if not the separator, adds the current chapter to the name string
                    name.append(line.charAt(i));
                }
            } else {    //if passed the separator, add teh current character to the price string
                price.append(line.charAt(i));
            }
        }

        //converts string from file to double
        double priceNum = Double.parseDouble(String.valueOf(price));

        //creates and returns a FoodItem
        FoodItem foodItem = new FoodItem(name.toString(), priceNum, menuIndicator.toString());
        return foodItem;
    }
}
