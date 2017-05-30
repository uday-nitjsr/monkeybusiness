package com.tal.Base.Element;

import com.google.gson.JsonElement;
import lombok.Getter;
import net.thucydides.core.annotations.findby.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by asus on 5/18/2017.
 */
public class Dropdown {
    public WebDriver driver;
    public Boolean removeTextFromXpath = false;
    @Getter
    public String dropdown_id;
    @Getter
    public String dropdown_class;
    @Getter
    public String dropdown_href;
    @Getter
    public String dropdown_text;

    public Dropdown(JsonElement dropdown_id, JsonElement dropdown_class, JsonElement dropdown_href, JsonElement dropdown_link,WebDriver driver){
        this.driver = driver;
        if(dropdown_id!=null){
            this.dropdown_id = dropdown_id.getAsString();
        }
        if(dropdown_class!=null){
            this.dropdown_class = dropdown_class.getAsString();
        }
        if(dropdown_href!=null){
            this.dropdown_href = dropdown_href.getAsString();
        }
        if(dropdown_link!=null){
            this.dropdown_text = dropdown_link.getAsString();
        }
    }

    //returns cssSelector locator based on the field values
    public String buildCssSelector(){
        String cssSelector = "";
        if (dropdown_id!=null){
            cssSelector += "#" + dropdown_id;
        }
        if (dropdown_href!=null){
            cssSelector += "[href='" + dropdown_href +"']";
        }
        //will be at the end also need to spilt base on space and dots
        if (dropdown_class!=null){
            cssSelector += "." + dropdown_class.replace(" ",".");
        }
        return cssSelector;
    }

    //return xpathSelector locator based on the field values
    public String buildXpathSelector(){
        String xpathSelector = "//*";

        if (dropdown_id!=null){
            xpathSelector += "[@id='" + dropdown_id + "']";
        }
        if (dropdown_class!=null){
            xpathSelector += "[@class='" + dropdown_class + "']";
        }
        if (dropdown_href!=null){
            xpathSelector += "[@href='" + dropdown_href + "']";
        }
        if (dropdown_text!=null && removeTextFromXpath.equals(false)) {
            xpathSelector += "[text()='" + dropdown_text +"']";
        }
        return xpathSelector;
    }

    public WebElement getdropdownWebElement() {
        if (dropdown_id!=null){
            List<WebElement> elements = driver.findElements(By.id(getDropdown_id()));
            if (elements.size()==1){
                return elements.get(0);
            }
        }
        else {
            if (dropdown_text!=null){
                try{
                    return driver.findElement(By.xpath(buildXpathSelector()));
                }
                catch (Exception e){
                    if (e.getMessage().contains("Unable to locate element: {\"method\":\"xpath\",\"selector\":\""+buildXpathSelector()+"\"}")){
                        removeTextFromXpath = true;
                        List<WebElement> elements=driver.findElements(By.xpath(buildXpathSelector()));
                        for (WebElement ele: elements){
                            if (ele.getText().equals(dropdown_text)){
                                return ele;
                            }
                        }
                        throw new NoSuchElementException("dropdown with text:"+dropdown_text+" not found");
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
