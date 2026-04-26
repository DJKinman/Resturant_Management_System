package com.example.resturant_management_system;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;

//public interface PointOfSale {
//    //gets the scene of the class to display instead
//    //https://stackoverflow.com/questions/77643917/switching-scenes-in-javafx-using-a-button
//    public void getScene();
//
//    //will place all the buttons into a pane that will be overlayed in the FoodItems area
//    public void displayTabItems();
//
//    //will basically just be a HBox that holds all the tab buttons
//    public void displayTabs();
//
//    //used to switch to a new tab of items
//    public void switchTab(Button tab);
//}

public abstract class PointOfSale extends Application {
    static protected ArrayList<FoodItem> menuItems;  //each subclass will overwrite in a static block to get the items of the correct menu only
    private FlowPane btnArea;   //this is where the button will appear
    static protected StackPane currentOrderArea; //will display all current orders
    static protected GridPane tabArea;   //holds the buttons to switch menu tabs, dont know what type of pane to use

    //static vars are used here because the subclasses will all share the versions from PointOfSale
    //they are constant versions

    PointOfSale(){
        //tyring this here so it is shared across all subclasses, may not work and have to delete later
        Stage primaryStage = new Stage();

        //initalizing the shared static vars
        menuItems = new ArrayList<>();
        currentOrderArea = new StackPane();
        tabArea = new GridPane();

        btnArea = new FlowPane();

        //places where each section will be
        //need to figure out how to limit the dimenstions of each area (btnArea will take up most of the screen)
        BorderPane root = new BorderPane();
        root.setLeft(currentOrderArea);
        root.setBottom(tabArea);
        root.setRight(btnArea);

        //do not include this bottom part, creates a 2nd window when application is launched
//        Scene scene = new Scene(root, 700, 500);
//        primaryStage.setTitle("Restaurant System");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage){

        //just some demo code to copy and paste
//        BorderPane root = new BorderPane();
//
//        Scene scene = new Scene(root, 700, 500);
//        primaryStage.setTitle("Restaurant System");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }
}