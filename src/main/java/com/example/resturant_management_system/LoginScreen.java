package com.example.resturant_management_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15));

        Label titleLabel = new Label("Restaurant Login");

        //Email UI section
        Label emailLabel = new Label("Login: ");
        TextField emailField = new TextField();

        //Password UI section
        Label passwordLabel = new Label("Password: ");
        TextField passwordField = new TextField();

        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(passwordLabel, 0, 4);
        gridPane.add(passwordField, 1, 4);

        //Can use code like this to delete items in FoodOrder from the current order
        emailLabel.setOnMouseClicked(e->{
            emailLabel.setText("Clicked!");
        });


        BorderPane root = new BorderPane();
        root.setTop(titleLabel);
        root.setCenter(gridPane);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Restaurant Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
