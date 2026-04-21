package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList implements FileLists {

    public  ArrayList<User> userList;

    public UserList() throws FileNotFoundException {
        this.userList = new ArrayList<>();

        getFromFile(new File("RegisteredUsers.txt"));

        //this next section is purely to test getting the users from the file
        for (User user : userList){
            System.out.println("Email: " + user.getEmail() + ", Password: " + user.getPassword()
            );
        }
    }

    //Checks if inputted user credential matches any inside list of registered users
    public Boolean validateUser(User attemptedLogin){
        //checks the attempted login credentials against all registered users
        for (User user : userList){
            //if there is a match, validate login
            if (attemptedLogin.getEmail().equals(user.getEmail()) && attemptedLogin.getPassword().equals(user.getPassword())){
                return true;
            }
        }

        //if no user matches, do not validate
        return false;
    }

    //creates a new user and updates RegisteredUsers.txt
    public void registerUser(User register) throws FileNotFoundException {
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

            //checks every registered user to make sure the email already isn't in use
            if (register.getEmail() == user.getEmail()){
                System.out.println("There is already a user registered with that email");

                return;
            }
        }

        userList.add(register);
        writeToFile(new File("RegisteredUsers.txt"));
    }

    @Override
    //Should run once at program execution
    //retrieves info from file
    public void getFromFile(File file) throws FileNotFoundException {
        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()){
            String line = readFile.nextLine();

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

            //creates new user based on info from file
            userList.add(new User(userEmail.toString(), userPassword.toString()));
        }

        //gotta make sure to close the file
        readFile.close();
    }

    @Override
    public void writeToFile(File file) throws FileNotFoundException {
        PrintWriter writeOutput = new PrintWriter(file);

        //prints each user in the ArrayList to the file, overwriting it
        for (User user : userList){
            writeOutput.println(user);
        }

        writeOutput.close();
    }
}
