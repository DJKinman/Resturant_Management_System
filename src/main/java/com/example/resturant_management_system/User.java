package com.example.resturant_management_system;

public class User {
    private String email;
    private String password;

    /**
     * Constructor for the User class
     * @param email the email of the user
     * @param password the password of the user
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the email of the user
     * @return email the email of the user
     */
    public String getEmail(){
        return email;
    }

    /**
     * Returns the password of the user
     * @return password the email of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * How the user information will be printed
     * @return the user information
     */
    @Override
    public String toString() {
        String result = email + "," + password;
        return result;
    }
}
