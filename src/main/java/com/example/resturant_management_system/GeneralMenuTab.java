package com.example.resturant_management_system;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class GeneralMenuTab extends PointOfSale{

    GeneralMenuTab(Scene mainScene) {
        super();    //calls PointOfSale constructor aswell
        this.mainScene = mainScene;

        this.setLeft(PointOfSale.currentOrderArea);

        //creates the button area
        btnArea = new FlowPane(10,10);

        //creates the buttons for and adds them to the button area
        for (FoodItem foodItem : Menu.getGeneralMenu()){
            Button btn = new Button(foodItem.getName());
            btnArea.getChildren().add(btn);

            //when the button is pressed, it adds the related FoodItem to the currentFoodOrder and creaets a new OrderArea
            //to be placed inside the currentOrderArea
            btn.setOnMouseClicked(e-> {
                currentFoodOrder.addFoodItem(foodItem);

                OrderArea orderArea = new OrderArea(foodItem, currentOrderArea);
                currentOrderArea.getChildren().add(orderArea);
                //prints the full current order to the console, mainly for debug purposes
                System.out.println("Current Order:\n" + currentFoodOrder);
            });
        }

        //Debug code to show where the Button area is
        btnArea.setBackground(Background.fill(Color.AQUA));

        //creates the General Menu Tab
        generalTab.setContent(btnArea);

        addTab(generalTab);
    }

    /**
     * Returns the tab area to be displayed
     * @return generalTab the tab for the General menu
     */
    @Override
    public Tab getTabArea(){
        return generalTab;
    }
}
