package com.tal.Base;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.tal.Base.Element.Button;
import com.tal.Base.Element.CheckBox;
import com.tal.Base.Element.Radio;
import com.tal.Base.Element.Textbox;
import com.tal.Base.Element.reporting.EmailableReport;
import com.tal.Base.json.ParseJson;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.*;
import java.net.URL;
import java.util.List;
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
        parseJson.parseThis(getJsonFromParseHub());
    }
    public JsonObject getJsonFromParseHub() throws FileNotFoundException {
        FileReader reader = new FileReader("D:\\Project\\parsehubImpl\\monkeybusiness\\src\\main\\resources\\sampleJson\\run_results_05_24.json");
        return new JsonParser().parse(reader).getAsJsonObject();
    }

    @AfterClass(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }

    /*public static void screenshot(WebDriver driver, String fileName){
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
        Fil
    }*/

    public void performAllButtonActions(List<Button> buttons,String elementName){
        for (Button button:buttons){
            System.out.println("Class:"+button.button_class+" ID:"+button.button_id
                    +" Href:"+button.button_href+" Text:"+button.button_text);
            try {
                WebElement ele = button.getButtonWebElement();
                ele.click();
                //check if button is clicked
                //take screenshot to page
                //Add to report
                EmailableReport.addRowToResult(elementName+button.button_text,Button.class.toString(),"click","");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void performAllCheckboxActions(List<CheckBox> checkBoxes, String elementName){
        for (CheckBox checkbox:checkBoxes){
            System.out.println("Class:"+checkbox.checkbox_class+" ID:"+checkbox.checkbox_id
                    +" Href:"+checkbox.checkbox_href+" Text:"+checkbox.checkbox_text);
            try {
                WebElement ele = checkbox.getcheckboxWebElement();
                ele.click();
                //check if button is clicked
                //take screenshot to page
                //Add to report
                EmailableReport.addRowToResult(elementName,CheckBox.class.toString(),"click","");
                //uncheck the checkbox
                ele.click();
                EmailableReport.addRowToResult(elementName,CheckBox.class.toString(),"click","");
                //take screenshot of page
                //add to report
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void peformAllTextboxActions(List<Textbox> textboxes){
        for (Textbox textbox:textboxes){
            System.out.println("Class:"+textbox.textbox_class+" ID:"+textbox.textbox_id
                    +" Href:"+textbox.textbox_href+" Text:"+textbox.textbox_text);
            try {
                WebElement ele = textbox.gettextboxWebElement();
                ele.sendKeys("dummy text");
                ele.clear();
                //check if button is clicked
                //take screenshot to page
                //Add to report
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void performAllRadioActions(List<Radio> radios){
        for (Radio radio:radios){
            System.out.println("Class:"+radio.radio_class+" ID:"+radio.radio_id
                    +" Href:"+radio.radio_href+" Text:"+radio.radio_text);
            try {
                WebElement ele = radio.getradioWebElement();
                ele.click();
                ele.click();
                //check if button is clicked
                //take screenshot to page
                //Add to report
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
