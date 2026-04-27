package com.example.resturant_management_system;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainScene extends Application {

    //this file is the glue that holds the tabArea buttons together
    //and just like glue I'm not sure why it works

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Restaurant");
        primaryStage.setScene(scene);
        primaryStage.show();

        //this works. dont know why but this works
        LoginScreen loginScreen = new LoginScreen();

        //i believe this is the important part
        loginScreen.start(primaryStage);
    }
}
