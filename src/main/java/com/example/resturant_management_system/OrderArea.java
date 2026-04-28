package com.example.resturant_management_system;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OrderArea extends HBox{
    private VBox OrderArea;

    public OrderArea(FoodItem foodItem, VBox orderArea){
        this.OrderArea = orderArea;

        //creates a text area that holds the name of the FoodItem added to the order
        Text foodName = new Text(foodItem.getName() + " ");
        //creates a text area that holds the price of the FoodItem added to the order
        Text foodPrice = new Text("$" + foodItem.getPrice() + " ");
        //creates a button to remove the specific FoodItem added from the order
        Button removeFoodItemBtn = new Button("X");

        orderArea.setSpacing(10);

        //when the removeFoodItemBtn is clicked, removes itself from the currentOrderArea
        //and from the currentFoodOrder static ArrayList from PointOfSale
        removeFoodItemBtn.setOnMouseClicked(e->{
            OrderArea.getChildren().remove(this);
            PointOfSale.currentFoodOrder.removeFoodItem(foodItem);
        });

        this.getChildren().addAll(foodName, foodPrice, removeFoodItemBtn);
    }
}
