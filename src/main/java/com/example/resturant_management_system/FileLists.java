package com.example.resturant_management_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

//used this to help do the generics stuff https://stackoverflow.com/questions/70809085/using-abstract-method-with-generic-list-as-return-type-in-java
public abstract class FileLists<T> {
    protected ArrayList<T> list;

    //creates an object(generic have to overwrite) from a line of a file and adds it to list
    public abstract T createArrayObj(String line);

    //Should run once at program execution
    //retrieves info from file
    public void getFromFile(File file) throws FileNotFoundException {
        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()){
            String line = readFile.nextLine();

            //creates new user based on info from file
            list.add(createArrayObj(line));
        }

        //gotta make sure to close the file
        readFile.close();
    }

    //Updates user info file when a new user is registered
    public void writeToFile(File file) throws FileNotFoundException{
        PrintWriter writeOutput = new PrintWriter(file);

        //prints each object in the ArrayList to the file, overwriting it
        for (T object : list){
            writeOutput.println(object);
        }

        writeOutput.close();
    }

}
