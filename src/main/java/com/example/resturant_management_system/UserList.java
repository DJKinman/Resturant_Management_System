package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList extends FileLists<User> {

    public UserList() {
        list = new ArrayList<>();
    }

    //Checks if inputted user credential matches any inside list of registered users
    public Boolean validateUser(User attemptedLogin){
        //checks the attempted login credentials against all registered users
        for (User user : list){
            //if there is a match, validate login
            if (attemptedLogin == user){
                return true;
            }
        }

        //if no user matches, do not validate
        return false;
    }

    //creates a new user and updates RegisteredUsers.txt
    public void registerUser(User register) throws FileNotFoundException {
        for (User user : list){
            //Makes sure there email
            //hopefully i did the regex correctly but i have no idea
            if (register.getEmail().isEmpty() || !register.getEmail().matches("[a-zA-Z]++[@gmail.com]")){
                System.out.println("Please insert a email");

                return;
            }

            //makes sure there is a password
            if (register.getPassword().isEmpty()){
                System.out.println("Please insert a password");

                return;
            }

            //checks every registered user to make sure the email already isn't in use
            if (register.getEmail() == user.getEmail()){
                System.out.println("There is already a user registered with that email");

                return;
            }
        }

        list.add(register);
        writeToFile(new File("RegisteredUsers.txt"));
    }

    @Override
    //creates a User object based on the RegisteredUsers.txt file
    public User createArrayObj(String line) {
        StringBuilder userEmail = new StringBuilder();
        StringBuilder userPassword = new StringBuilder();

        //checks to see if comma seperating the email and password was passed or not
        Boolean passedSeperator = false;

        //loops through the line character by character
        for (int i = 0; i < line.length(); i ++){

            //if not passed the separator
            if (!passedSeperator){
                //checks if current character is the separator
                if (line.charAt(i) == ','){
                    passedSeperator = true;
                } else {    //if not the separator, adds the current chapter to the userEmail string
                    userEmail.append(line.charAt(i));
                }
            } else {    //if passed the separator, add teh current character to the userPassword string
                userPassword.append(line.charAt(i));
            }
        }

        //creates and returns a new user
        User user = new User(userEmail.toString(), userPassword.toString());
        return user;
    }
}
