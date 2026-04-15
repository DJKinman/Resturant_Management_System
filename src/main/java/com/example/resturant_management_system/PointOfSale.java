package com.example.resturant_management_system;

import javafx.scene.control.Button;

public interface PointOfSale {
    //gets the scene of the class to display instead
    //https://stackoverflow.com/questions/77643917/switching-scenes-in-javafx-using-a-button
    public void getScene();

    //will place all the buttons into a pane that will be overlayed in the FoodItems area
    public void displayTabItems();

    //will basically just be a HBox that holds all the tab buttons
    public void displayTabs();

    //used to switch to a new tab of items
    public void switchTab(Button tab);
}
