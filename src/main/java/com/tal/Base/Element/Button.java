package com.tal.Base.Element;

import com.google.gson.JsonElement;
import lombok.Getter;
import net.thucydides.core.annotations.findby.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by asus on 5/17/2017.
 */
public class Button {

    public WebDriver driver;
    Boolean removeTextFromXpath = false;
    @Getter
    public String button_id;
    @Getter
    public String button_class;
    @Getter
    public String button_href;
    @Getter
    public String button_text;

    public Button(JsonElement button_id, JsonElement button_class, JsonElement button_href, JsonElement button_text,WebDriver driver) {
        this.driver = driver;
        if (button_id != null) {
            this.button_id = button_id.getAsString();
        }
        if (button_class != null) {
            this.button_class = button_class.getAsString();
        }
        if (button_href != null) {
            this.button_href = button_href.getAsString();
        }
        if (button_text != null) {
            this.button_text = button_text.getAsString();
        }
    }

    //returns cssSelector locator based on the field values
    public String buildCssSelector(){
        String cssSelector = "";
        if (button_id!=null){
            cssSelector += "#" + button_id;
        }
        if (button_href!=null){
            cssSelector += "[href='" + button_href +"']";
        }
        //will be at the end also need to spilt base on space and dots
        if (button_class!=null){
            cssSelector += "." + button_class.replace(" ",".");
        }
        return cssSelector;
    }

    //return xpathSelector locator based on the field values
    public String buildXpathSelector(){
        String xpathSelector = "//*";
        if (button_id!=null){
            xpathSelector += "[@id='" + button_id + "']";
        }
        if (button_class!=null){
            xpathSelector += "[@class='" + button_class + "']";
        }
        if (button_href!=null){
            xpathSelector += "[@href='" + button_href + "']";
        }
        if (button_text!=null && removeTextFromXpath.equals(false)) {
            xpathSelector += "[text()='" + button_text +"']";
        }
        return xpathSelector;
    }

    public WebElement getButtonWebElement() {
        if (button_id!=null){
            List<WebElement> elements = driver.findElements(By.id(getButton_id()));
            if (elements.size()==1){
                return elements.get(0);
            }
        }
        else {
            if (button_text!=null){
                try{
                    return driver.findElement(By.xpath(buildXpathSelector()));
                }
                catch (Exception e){
                    if (e.getMessage().contains("Unable to locate element: {\"method\":\"xpath\",\"selector\":\""+buildXpathSelector()+"\"}")){
                        removeTextFromXpath = true;
                        List<WebElement> elements=driver.findElements(By.xpath(buildXpathSelector()));
                        for (WebElement ele: elements){
                            if (ele.getText().equals(button_text)){
                                return ele;
                            }
                        }
                        throw new NoSuchElementException("Button with text:"+button_text+" not found");
                    }
                }
            }
            else {
                return driver.findElement(By.cssSelector(buildCssSelector()));
            }
        }
        return null;
    }

}