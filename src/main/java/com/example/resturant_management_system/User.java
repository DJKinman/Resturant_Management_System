package com.example.resturant_management_system;

public class User {
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        String result = email + "," + password;
        return result;
    }
}
