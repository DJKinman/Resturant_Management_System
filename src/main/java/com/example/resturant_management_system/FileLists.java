package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;

public interface FileLists{
    //creates an ArrayList of objects based on file contents
    public void getFromFile(File file) throws FileNotFoundException;

    //Updates file based on ArrayList of objects
    public void writeToFile(File file) throws FileNotFoundException;
}
