package com.example.resturant_management_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//Most likely going to change the name of this class later
public class JavaFX extends PointOfSale {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        GridPane testPane = new GridPane(10,10);
        testPane.setPadding(new Insets(15));
        root.getChildren().add(testPane);

        Button testBtn = new Button("Change Scene");
        testPane.add(testBtn, 1,1);

        Scene scene = new Scene(root, 700, 500);

        //click the button to show how the scene changes roots
        testBtn.setOnMouseClicked(e -> {
            BorderPane newRoot = new BorderPane();
            scene.setRoot(newRoot);
        });

        primaryStage.setTitle("Class is grandchild of Application test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
