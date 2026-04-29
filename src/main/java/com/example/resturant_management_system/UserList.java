package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserList implements FileLists {

    public  ArrayList<User> userList;

    /**
     * Constructor the for UserList class
     * @throws FileNotFoundException when the file that hodls the registered users is not found
     */
    public UserList() throws FileNotFoundException {
        this.userList = new ArrayList<>();

        //gets all the users from the RegisteredUsers.txt file
        getFromFile(new File("RegisteredUsers.txt"));

//        //this next section is purely to test getting the users from the file
//        System.out.println("DEBUG REMOVE LATER:\n");
//        for (User user : userList){
//            System.out.println("Email: " + user.getEmail() + ", Password: " + user.getPassword());
//        }
    }

    /**
     * Checks if inputted user credential matches any inside list of registered users
     * @param attemptedLogin the user credintails the user is trying to login with
     * @return if successfuly logged in or not
     */
    public Boolean validateUser(User attemptedLogin){
        //checks the attempted login credentials against all registered users
        for (User user : userList){

            //if there is a match, validate login
            if (attemptedLogin.getEmail().equalsIgnoreCase(user.getEmail()) && attemptedLogin.getPassword().equals(user.getPassword())){
                return true;
            }
        }

        //if no user matches, do not validate
        return false;
    }

    /**
     * creates a new user and updates RegisteredUsers.txt
     * @param register the new user to register
     * @return integer value that works as a error code
     * @throws FileNotFoundException when the file that hodls all the registered users is not found
     */
    public int registerUser(User register) throws FileNotFoundException {
        //returned integer will be used as a sort of error code

        for (User user : userList){
            //Makes sure there is an email and that it matches the regex pattern
            if (register.getEmail().isEmpty() || !register.getEmail().matches("[a-zA-Z]+(@gmail.com)")){
                System.out.println("Please submit a valid email");

                return 1;
            }

            //checks every registered user to make sure the email already isn't in use
            if (register.getEmail().equalsIgnoreCase(user.getEmail())){
                System.out.println("There is already a user registered with that email");

                return 2;
            }
        }

        //if no error is found with the registered credentials, adds to the userList ArrayList and updates teh RegisteredUsers.txt file
        userList.add(register);
        writeToFile(new File("RegisteredUsers.txt"));

        return 0;
    }

    /**
     * Encrypts the users in userList before writing to file
     * @param line the line that holds the user information to encrypt
     * @param key how far each letter is to be shifted
     * @return the encrypted user data
     */
    public String encryptData(String line, int key){
        String[] splits= line.split(",");
        String encryptedData="";
        for (int i=0;i< splits.length;i++) {
            if (i== splits.length-1) {
                encryptedData += CaeserCipher.encrypt(splits[i], key);
            } else {
                encryptedData += CaeserCipher.encrypt(splits[i], key)+",";
            }

        }

        return encryptedData;
    }

    /**
     * Decrypts each user from the file
     * @param line the line that holds the encrypted user data
     * @return the decrypted user data
     */
    public String decryptData(String line){
        int key = 15;
        //key of 15 was choosen arbitrarily

        return encryptData(line, 26 - 15);
    }

    /**
     * Gets all the uesrs from the RegisteredUsers.txt file
     * @param file the file that holds all the registered users
     * @throws FileNotFoundException when the file that holds all the registered users is not found
     */
    @Override
    public void getFromFile(File file) throws FileNotFoundException {
        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()){
            String line = readFile.nextLine();
            line = decryptData(line);

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

    /**
     * Writes all the uesrs to the RegisteredUsers.txt file
     * @param file the file that holds all the registered users
     * @throws FileNotFoundException when the file that holds all the registered users is not found
     */
    @Override
    public void writeToFile(File file) throws FileNotFoundException {
        PrintWriter writeOutput = new PrintWriter(file);

        //prints each user in the ArrayList to the file, overwriting it
        for (User user : userList){
            String encryptedUser = encryptData(user.toString(), 15);
            writeOutput.println(encryptedUser);
        }

        writeOutput.close();
    }
}
