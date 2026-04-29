package com.example.resturant_management_system;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;

public class MainMenuScreen extends PointOfSale{
    /**
     * Constructor for the MainMenuScreen class
     * @param mainScene the scene that is displayed
     */
    MainMenuScreen(Scene mainScene){
        this.mainScene = mainScene;

        ViewAllOrdersMenu viewAllOrdersMenu = new ViewAllOrdersMenu();

        HBox BtnArea = new HBox();

        //creates the objects for each tab to switch between
        GeneralMenuTab generalMenuTab = new GeneralMenuTab(mainScene);
        KidsMenuTab kidsMenuTab = new KidsMenuTab(mainScene);
        DrinksMenuTab drinksMenuTab = new DrinksMenuTab(mainScene);

        //adds the other tabs to the GeneralMenuTab so that they can be switched between
        //(GeneralMenuTab is the first to appear after the login so it is treated like the base)
        generalMenuTab.addTab(kidsMenuTab.getTabArea());
        generalMenuTab.addTab(drinksMenuTab.getTabArea());

        //Buttons to either create a new order or view all orders made
        Button createOrderBtn = new Button("Create Order");
        Button viewOrdersBtn = new Button("View orders");

        BtnArea.getChildren().addAll(createOrderBtn, viewOrdersBtn);

        //lets the user create a new order
        createOrderBtn.setOnMouseClicked(e-> {
            mainScene.setRoot(generalMenuTab);
        });

        //lets the user view all orders
        viewOrdersBtn.setOnMouseClicked(e-> {
            viewAllOrdersMenu.reloadMenu();
            mainScene.setRoot(viewAllOrdersMenu);
        });

        this.setCenter(BtnArea);
    }

    @Override
    public Tab getTabArea() {
        return null;
    }
}
