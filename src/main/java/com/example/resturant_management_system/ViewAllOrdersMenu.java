package com.example.resturant_management_system;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;

public class ViewAllOrdersMenu extends PointOfSale{

    /**
     * Constructor for the ViewAllOrdersMenu
     */
    ViewAllOrdersMenu(){
        //calls the reload method
        //orignally had it in the constructor but each then the class would not change when orders were added or removed
        reloadMenu();
    }

    /**
     * Reloads the ViewAllOrders menu class with the correct updated orders
     */
    public void reloadMenu(){
        GridPane gridPane = new GridPane(10, 10);

        //base elemnets that will always appear no matter if there are orders to display or not
        Label orderLabel = new Label("All Orders:");
        Button backBtn = new Button("Back");

        gridPane.add(orderLabel, 1, 0);
        gridPane.add(backBtn, 0,0);

        //lets the user go back to the main menu to create a new order
        backBtn.setOnMouseClicked(e-> {
            mainScene.setRoot(LoginScreen.mainMenuScreen);
        });

        int row = 1;
        //adds a new section for every order recorded
        for (FoodOrder order : FoodOrder.allOrders){
            //creates the fields to add for each order
            Text orderID = new Text("Order ID: " + order.getId());
            Text orderTotal = new Text("Total: $" + String.format("%.2f",order.getTotal()));
            Button removeOrderBtn = new Button("X");

            //adds the fields to the gridpane
            gridPane.add(orderID, 1, row);
            gridPane.add(orderTotal, 2, row);
            gridPane.add(removeOrderBtn, 3, row);

            //removes the selected order from the file
            removeOrderBtn.setOnMouseClicked(e->{
                FoodOrder.allOrders.remove(order);
                reloadMenu();

                //this is here just to update the file
                FoodOrder temp = new FoodOrder();
                try {
                    temp.writeToFile(new File("CompletedOrders.txt"));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });

            row++;
        }

        this.setCenter(gridPane);
    }

    @Override
    public Tab getTabArea() {
        return null;
    }
}
