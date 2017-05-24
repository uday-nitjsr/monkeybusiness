package com.tal.Base;

import com.tal.Base.json.ParseJson;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by asus on 5/24/2017.
 */
public class MasterRunner {
    WebDriver driver;
    Properties properties;
    public String url;
    public String browser;
    public ParseJson parseJson;

    //to read properties and
    public void readProp() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("scrapper.properties");
        if (inputStream == null) {
            throw new FileNotFoundException("inputStream is null");
        }
        properties = new Properties();
        properties.load(inputStream);
        url = properties.getProperty("url");
        browser = properties.getProperty("browser","firefox");
    }

    @BeforeClass
    public void initialize() throws Exception {
        readProp();
        if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("chrome")){
            URL fileUrl = this.getClass().getClassLoader().getResource("OtherBrowserDrivers/chromedriver.exe");
            String filePath = fileUrl.getPath();
            System.setProperty("webdriver.chrome.driver",filePath);
            driver = new ChromeDriver();
        }
        //maximize browser
        driver.manage().window().maximize();

        //hit app url
        driver.get(url);

        //create object of ParseJson
        parseJson = new ParseJson(driver);
        parseJson.parseThis(parseJson.obj,false,null);
    }

    @AfterClass(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }
}
