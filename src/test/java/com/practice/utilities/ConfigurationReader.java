package com.practice.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
    Re-usable method that will be reading from configuration.reader file
 */
public class ConfigurationReader {

  //Creating properties object
    private static final Properties properties = new Properties();

    static {
        try {
            //Loading the file into FileInputStream
            FileInputStream file = new FileInputStream("configuration.properties");

            //Loading properties object with the file(configuration.properties)
            properties.load(file);

            //Closing the file
            file.close();

        } catch (IOException e) {
            System.out.println("File not found in Configuration properties.");
        }

    }

    //Using the above created Logic to create a reusable static object
    public static String getProperty(String keyWord) {
        return properties.getProperty(keyWord);
    }

}

