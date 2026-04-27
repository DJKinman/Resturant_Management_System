package com.example.resturant_management_system;

import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

public abstract class PointOfSale extends BorderPane {
    static protected ArrayList<FoodItem> menuItems;  //each subclass will overwrite in a static block to get the items of the correct menu only
    protected FlowPane btnArea;   //this is where the button will appear
    public static VBox currentOrderArea; //will display all current orders
    protected TabPane tabArea;   //holds the buttons to switch menu tabs, dont know what type of pane to use
    protected Scene mainScene;     //the main scene
    public static FoodOrder currentFoodOrder;    //holds the current FoodOrder object
    protected Tab generalTab;
    protected Tab kidsTab;
    protected Tab drinksTab;

    //static vars are used here because the subclasses will all share the versions from PointOfSale
    //they are constant versions

    static {
        currentFoodOrder = new FoodOrder();
    }

    PointOfSale(){
        //tyring this here so it is shared across all subclasses, may not work and have to delete later

        //initalizing the shared static vars
        menuItems = new ArrayList<>();
        tabArea = new TabPane();
        tabArea.setSide(Side.BOTTOM);

        generalTab = new Tab("General");
        kidsTab = new Tab("Kids");
        drinksTab = new Tab("Drinks");

        //creates the Current Order area
        if (currentOrderArea == null){
            currentOrderArea = new VBox();
            Label currentOrderText = new Label("Currnet Order:");

            Button completeOrderBtn = new Button("Complete");
            completeOrderBtn.setOnMouseClicked(e-> {
                //creates the alert that displays the receipt
                Alert receipt = new Alert(Alert.AlertType.INFORMATION);
                receipt.setTitle("Receipt");
                //https://stackoverflow.com/questions/8819842/best-way-to-format-a-double-value-to-2-decimal-places
                receipt.setHeaderText("Total: " + String.format("%.2f", currentFoodOrder.getTotal()));
                receipt.setContentText(currentFoodOrder.toString());
                receipt.showAndWait();

                //clears the current order
                currentFoodOrder.clearOrder();
                //removes all items from teh currentOrderArea
                currentOrderArea.getChildren().clear();

                //readds the currentOrderText and completeOrderBtn to the cleared currentOrderArea
                currentOrderArea.getChildren().addAll(currentOrderText, completeOrderBtn);

            });

            currentOrderArea.getChildren().addAll(currentOrderText, completeOrderBtn);
            //sets the currentOrdderArea to have a set width
            currentOrderArea.setMinWidth(200);

            //Debug code to show where the Current Order Area is
            currentOrderArea.setBackground(Background.fill(Color.DARKRED));
        }

        //places the GUI fields into the correct areas
        this.setCenter(tabArea);


        //places where each section will be
        //need to figure out how to limit the dimenstions of each area (btnArea will take up most of the screen)
//        BorderPane root = new BorderPane();
//        root.setLeft(currentOrderArea);
//        root.setBottom(tabArea);
//        root.setRight(btnArea);

        //do not include this bottom part, creates a 2nd window when application is launched
//        Scene scene = new Scene(root, 700, 500);
//        primaryStage.setTitle("Restaurant System");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    protected void addTab(Tab tab){
        tabArea.getTabs().add(tab);
    }

}