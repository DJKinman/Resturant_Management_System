package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList {
    ArrayList<User> userList;

    public UserList() {
        userList = new ArrayList<>();
    }

    //Should run once at program exeucution
    //retrieves user info from file
    public void getUsersFromFile() throws FileNotFoundException {
        Scanner readUserFile = new Scanner(new File("RegisteredUsers.txt"));

         while (readUserFile.hasNext()){
             //tells the scanner when to stop reading (includes all whitespace, commas, and dashes/minus signs)
             readUserFile.useDelimiter("[\\s,-]+");

             //parses date into separate variables to combine into date object
             String userEmail = readUserFile.next();
             String userPassword = readUserFile.next();

             //creates new user based on info from file
             userList.add(new User(userEmail, userPassword));
         }

         //gotta make sure to close the file
         readUserFile.close();
    }

    //Updates user info file when a new user is registered
    public void writeUsersToFile() throws FileNotFoundException {
        PrintWriter writeOutput = new PrintWriter("RegisteredUsers.txt");

        //prints each user in the file
        for (User user : userList){
            writeOutput.println(user);
        }

        writeOutput.close();
    }

    //Checks if inputted user credential matches any inside list of registered users
    public Boolean validateUser(User attemptedLogin){
        //checks the attempted login credentials against all registered users
        for (User user : userList){
            //if there is a match, validate login
            if (attemptedLogin == user){
                return true;
            }
        }

        //if no user matches, do not validate
        return false;
    }

    public void registerUser(User register){
        for (User user : userList){
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

            //checks every registered user to makesure the email already isnt in use
            if (register.getEmail() == user.getEmail()){
                System.out.println("There is already a user registered with that email");

                return;
            }
        }

        userList.add(register);
    }
}
