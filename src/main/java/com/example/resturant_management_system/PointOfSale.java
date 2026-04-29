package com.example.resturant_management_system;

import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class PointOfSale extends BorderPane {
    static protected ArrayList<FoodItem> menuItems;  //each subclass will overwrite in a static block to get the items of the correct menu only
    protected FlowPane btnArea;   //this is where the button will appear
    public static VBox currentOrderArea; //will display all current orders
    protected TabPane tabArea;   //holds the buttons to switch menu tabs, dont know what type of pane to use
    protected static Scene mainScene;     //the main scene
    public static FoodOrder currentFoodOrder;    //holds the current FoodOrder object
    protected Tab generalTab;
    protected Tab kidsTab;
    protected Tab drinksTab;

    //static vars are used here because the subclasses/children will all share the versions from PointOfSale
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

        //creates the tabs to switch between
        generalTab = new Tab("General");
        kidsTab = new Tab("Kids");
        drinksTab = new Tab("Drinks");

        //creates the Current Order area
        //IntelliJ was saying currentOrderArea may be null so I added this
        if (currentOrderArea == null){
            currentOrderArea = new VBox();
            Label currentOrderLabel = new Label("Currnet Order:");

            //button that completes the transaction and clears all related fields
            Button completeOrderBtn = new Button("Complete");
            completeOrderBtn.setOnMouseClicked(e-> {
                //creates the alert that displays the receipt
                Alert receipt = new Alert(Alert.AlertType.INFORMATION);
                receipt.setTitle("Receipt");
                //https://stackoverflow.com/questions/8819842/best-way-to-format-a-double-value-to-2-decimal-places
                receipt.setHeaderText("Total: " + String.format("%.2f", currentFoodOrder.getTotal()));
                receipt.setContentText(currentFoodOrder.toString());
                receipt.showAndWait();

                //adds the currentFoodOrder to an ArrayList of all orders and prints it to a text file
                try {
                    currentFoodOrder.addToAllOrders();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                currentFoodOrder = new FoodOrder();
                //clears the current order
                currentFoodOrder.clearOrder();
                //removes all items from teh currentOrderArea
                currentOrderArea.getChildren().clear();

                //readds the currentOrderText and completeOrderBtn to the cleared currentOrderArea
                currentOrderArea.getChildren().addAll(currentOrderLabel, completeOrderBtn);

                //goes back to the main menu screen
                mainScene.setRoot(LoginScreen.mainMenuScreen);
            });

            //adds the label and button to complete the current order
            currentOrderArea.getChildren().addAll(currentOrderLabel, completeOrderBtn);
            //sets the currentOrdderArea to have a set width
            currentOrderArea.setMinWidth(200);

            //Changes the colour of the currentOrderArea to differentiate it
            currentOrderArea.setBackground(Background.fill(Color.DARKRED));
        }

        //places the GUI fields into the correct areas
        this.setCenter(tabArea);
    }

    /**
     * Returns the tab of the object called
     * @param tab   the tab the subclass of PointOfSale has created to be displayed
     */
    protected void addTab(Tab tab){
        tabArea.getTabs().add(tab);
    }

    public abstract Tab getTabArea();

}