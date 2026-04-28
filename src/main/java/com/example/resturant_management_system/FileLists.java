package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;

public interface FileLists{
    /**
     * Creates an ArrayList of objects based on file contents
     * @param file the file that holds the data
     * @throws FileNotFoundException when the file is not found
     */
    public void getFromFile(File file) throws FileNotFoundException;

    /**
     * Updates file based on ArrayList of objects
     * @param file the file that holds the data
     * @throws FileNotFoundException when the file is not found
     */
    public void writeToFile(File file) throws FileNotFoundException;
}
