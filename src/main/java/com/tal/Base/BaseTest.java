package com.tal.Base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by asus on 4/19/2017.
 */
public class BaseTest {

    public static String PROPERTY_FILE_PATH =  "scrapper.properties";
    Properties properties;

    public void readProp() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_PATH);
        if (inputStream == null){
            throw new FileNotFoundException("incorrect property file name");
        }
        properties.load(inputStream);
    }

    public void setup(){
        //read property file
    }
}
