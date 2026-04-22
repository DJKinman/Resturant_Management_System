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

import java.io.FileNotFoundException;

public class LoginScreen extends Application {

    public static void main(String[] args) {launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        UserList userList = new UserList();

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

        //Login Button
        Button loginBtn = new Button("Login");

        //Register Button
        Button registerBtn = new Button("Register");

        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(passwordLabel, 0, 4);
        gridPane.add(passwordField, 1, 4);
        gridPane.add(loginBtn, 1, 6);
        gridPane.add(registerBtn, 0, 6);

        //Can use code like this to delete items in FoodOrder from the current order
        emailLabel.setOnMouseClicked(e->{
            emailLabel.setText("Clicked!");
        });

        loginBtn.setOnMouseClicked(event -> {
            if (userList.validateUser(new User(emailField.getText().trim(), passwordField.getText().trim())) == true){

                //this is a test for now
                loginBtn.setText("Success!");
            } else {
                loginBtn.setText("Failed");
            }
        });

        registerBtn.setOnAction(event -> {
            if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                //probably make a seperate label to say this idk
                registerBtn.setText("Please input a valid username and password");
            }
            else {
                User newUser = new User(emailField.getText().trim(), passwordField.getText().trim());
                try {
                    userList.registerUser(newUser);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                //again probably make a new label to show results
                registerBtn.setText("Registered Successfully");
            }
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
