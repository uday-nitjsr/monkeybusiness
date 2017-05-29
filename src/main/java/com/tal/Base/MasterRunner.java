package com.tal.Base;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tal.Base.Element.Button;
import com.tal.Base.Element.CheckBox;
import com.tal.Base.Element.Radio;
import com.tal.Base.Element.Textbox;
import com.tal.Base.Element.reporting.EmailableReport;
import com.tal.Base.json.ParseJson;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * Created by asus on 5/24/2017.
 */
@Listeners({EmailableReport.class})
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
        EmailableReport.generateReport();
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
        FileReader reader = new FileReader("D:\\Project\\parsehubImpl\\monkeybusiness\\src\\main\\resources\\sampleJson\\run_results_05_29.json");
        return new JsonParser().parse(reader).getAsJsonObject();
    }

    @AfterClass(alwaysRun = true)
    public void teardown(){
        driver.quit();
    }

    public static void screenshot(WebDriver driver, String fileName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir")+"/screenshot";
        FileUtils.copyFile(scrFile,new File(filePath+"/"+fileName+".png"));
    }

    public void performAllButtonActions(List<Button> buttons,String elementName){
        for (Button button:buttons){
            System.out.println("Class:"+button.button_class+" ID:"+button.button_id
                    +" Href:"+button.button_href+" Text:"+button.button_text);
            try {
                WebElement ele = button.getButtonWebElement();
                ele.click();
                //check if button is clicked
                //take screenshot to page
                screenshot(driver,elementName+button.button_text);
                //Add to report
                EmailableReport.addRowToResult(elementName+button.button_text,Button.class.getSimpleName(),"click","screenshot/"+elementName+button.button_text+".png");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                try {
                    screenshot(driver,elementName+button.button_text);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    EmailableReport.addRowToResult(elementName+button.button_text,Button.class.getSimpleName(),"Click attempted"+e.getMessage()+"\nNo screenshot available","");
                    break;
                }
                EmailableReport.addRowToResult(elementName+button.button_text,Button.class.getSimpleName(),"click attempt failed:"+e.getMessage(),"screenshot/"+elementName+button.button_text+".png");
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
                screenshot(driver,elementName+checkbox.checkbox_id+"check");
                //Add to report
                EmailableReport.addRowToResult(elementName,CheckBox.class.getSimpleName(),"click to check","screenshot/"+elementName+checkbox.checkbox_id+"check.png");
                //uncheck the checkbox
                ele.click();
                screenshot(driver,elementName+checkbox.checkbox_id+"uncheck");
                EmailableReport.addRowToResult(elementName,CheckBox.class.getSimpleName(),"click to uncheck","screenshot/"+elementName+checkbox.checkbox_id+"uncheck.png");
                //take screenshot of page
                //add to report
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                try {
                    screenshot(driver,elementName+checkbox.checkbox_id+"failed");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    EmailableReport.addRowToResult(elementName,CheckBox.class.getSimpleName(),"Click attempted"+e.getMessage()+"\nNo screenshot available","");
                }
                EmailableReport.addRowToResult(elementName,CheckBox.class.getSimpleName(),"click but failed","screenshot/"+elementName+checkbox.checkbox_id+"failed.png");
            }
        }
    }

    public void peformAllTextboxActions(List<Textbox> textboxes,String elementName){
        for (Textbox textbox:textboxes){
            System.out.println("Class:"+textbox.textbox_class+" ID:"+textbox.textbox_id
                    +" Href:"+textbox.textbox_href+" Text:"+textbox.textbox_text);
            try {
                WebElement ele = textbox.gettextboxWebElement();
                ele.sendKeys("dummy text");
                screenshot(driver,elementName+textbox.textbox_id);
                //Add to report
                EmailableReport.addRowToResult(elementName,Textbox.class.getSimpleName(),"sent dummy text","screenshot/"+elementName+textbox.textbox_id+".png");
                ele.clear();
                screenshot(driver,elementName+textbox.textbox_id+"clear");
                //Add to report
                EmailableReport.addRowToResult(elementName,Textbox.class.getSimpleName(),"Clear text","screenshot/"+elementName+textbox.textbox_id+"clear.png");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                try {
                    screenshot(driver,elementName+textbox.textbox_id+"failed");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    EmailableReport.addRowToResult(elementName,Textbox.class.getSimpleName(),"action attempted"+e.getMessage()+"\nNo screenshot available","");
                }
                EmailableReport.addRowToResult(elementName,Textbox.class.getSimpleName(),"failed","screenshot/"+elementName+textbox.textbox_id+"failed.png");
            }
        }
    }

    public void performAllRadioActions(List<Radio> radios,String elementName){
        for (Radio radio:radios){
            System.out.println("Class:"+radio.radio_class+" ID:"+radio.radio_id
                    +" Href:"+radio.radio_href+" Text:"+radio.radio_text);
            try {
                WebElement ele = radio.getradioWebElement();
                ele.click();
                screenshot(driver,elementName+radio.radio_text);
                //Add to report
                EmailableReport.addRowToResult(elementName+radio.radio_text,Radio.class.getSimpleName().toString(),"click","screenshot/"+elementName+radio.radio_text+".png");
                ele.click();
                screenshot(driver,elementName+radio.radio_text+"again");
                //Add to report
                EmailableReport.addRowToResult(elementName+radio.radio_text,Radio.class.getSimpleName().toString(),"click","screenshot/"+elementName+radio.radio_text+"again.png");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                try {
                    screenshot(driver,elementName+radio.radio_text);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    EmailableReport.addRowToResult(elementName+radio.radio_text,Button.class.toString(),"Click attempted"+e.getMessage()+"\nNo screenshot available","");
                    break;
                }
                EmailableReport.addRowToResult(elementName+radio.radio_text,Button.class.toString(),"click attempt failed:"+e.getMessage(),"screenshot/"+elementName+radio.radio_text+".png");
            }
        }
    }
}
