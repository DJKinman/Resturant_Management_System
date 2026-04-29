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

    static MainMenuScreen mainMenuScreen;

    public static void main(String[] args) {launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        UserList userList = new UserList();

        //creates the GridPane the LoginScreen wil be placed on
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15));

        //creates the label for the LoginScreen
        Label titleLabel = new Label("Restaurant Login");

        //Email UI section
        Label emailLabel = new Label("Login: ");
        TextField emailField = new TextField();
        emailLabel.setStyle("-fx-text-fill: blue;");

        //Password UI section
        Label passwordLabel = new Label("Password: ");
        TextField passwordField = new TextField();
        passwordLabel.setStyle("-fx-text-fill: blue;");

        //Login Button
        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-background-color: brown; -fx-text-fill: white;");

        //Register Button
        Button registerBtn = new Button("Register");
        registerBtn.setStyle("-fx-background-color: brown; -fx-text-fill: white;");

        //adds all the elements to the GridPane
        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(passwordLabel, 0, 4);
        gridPane.add(passwordField, 1, 4);
        gridPane.add(loginBtn, 1, 6);
        gridPane.add(registerBtn, 0, 6);

        //creates the root and adds the elements to it
        BorderPane root = new BorderPane();
        root.setTop(titleLabel);
        root.setCenter(gridPane);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        //creates the scene for the application
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Restaurant Login");
        primaryStage.setScene(scene);
        primaryStage.show();

        mainMenuScreen = new MainMenuScreen(scene);

        //Debug button to skip login screen credentials
        Button debugBtn = new Button("DEBUG SKIP");
        gridPane.add(debugBtn, 3, 6);
        debugBtn.setOnMouseClicked(e-> {
            scene.setRoot(mainMenuScreen);
        });

        //if login is successful changes the root to GeneralMenuTab
        loginBtn.setOnMouseClicked(event -> {
            if (userList.validateUser(new User(emailField.getText().trim(), passwordField.getText().trim())) == true){
                scene.setRoot(mainMenuScreen);
            } else {
                loginBtn.setText("Failed");
            }
        });

        //logic for when the register new user button is pressed
        registerBtn.setOnAction(event -> {
            // Just copied and pasted the above code of validating user and changed it to false instead of true
            // I know its long so it looks a bit too much. (idk how to fix that)
            if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()){
                //probably make a seperate label to say this idk
                registerBtn.setText("Invalid");
                System.out.println("Please enter a email and password");
            } else if(userList.validateUser(new User(emailField.getText().trim(), passwordField.getText().trim())) == false ){
                //probably make a seperate label to say this idk
                registerBtn.setText("Invalid");
                System.out.println("Please enter a email and password");
            } else{
                //creates a new user based on the information inputted
                User newUser = new User(emailField.getText().trim(), passwordField.getText().trim());
                try {
                    //trys adding the user the the userList and returns an error code
                    int registerUserErrorCode = userList.registerUser(newUser);

                    if (registerUserErrorCode == 0){
                        //code 0 means everything went as expected
                        return;
                    } else if(registerUserErrorCode == 1){
                        //code 1 means an invalid email was inputted
                        System.out.println("Invalid Email");
                        registerBtn.setText("Invalid");
                    } else if (registerUserErrorCode == 2){
                        //code 2 means there is already a user with that email registered
                        System.out.println("There is already a user with that email");
                        registerBtn.setText("Invalid");
                    }
                } catch (FileNotFoundException e) {
                    //happens if unable to find the file with the registered users
                    throw new RuntimeException(e);
                }
                //lets the user know they successfully registered
                registerBtn.setText("Success");

            }
        });
    }
}
