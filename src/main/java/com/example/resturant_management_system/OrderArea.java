package com.example.resturant_management_system;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class OrderArea extends HBox{
    private VBox OrderArea;

    public OrderArea(FoodItem foodItem, VBox orderArea){
        this.OrderArea = orderArea;
        Text foodName = new Text(foodItem.getName());
        Text foodPrice = new Text(String.valueOf(foodItem.getPrice()));
        Button removeFoodItemBtn = new Button("X");

        this.getChildren().addAll(foodName, foodPrice, removeFoodItemBtn);

        removeFoodItemBtn.setOnMouseClicked(e->{
            OrderArea.getChildren().remove(this);
            PointOfSale.currentFoodOrder.removeFoodItem(foodItem);
        });
    }

    public void setOrderArea(VBox currentOrderContainer){
        this.OrderArea = currentOrderContainer;
    }
}
