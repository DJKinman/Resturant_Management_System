package com.example.resturant_management_system;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//Most likely going to change the name of this class later
public class JavaFX extends PointOfSale {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Class is grandchild of Application test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
