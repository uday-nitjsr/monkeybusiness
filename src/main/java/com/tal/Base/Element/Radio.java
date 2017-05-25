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
public class Radio {
    public WebDriver driver;
    Boolean removeTextFromXpath = false;
    @Getter
    public String radio_id;
    @Getter
    public String radio_class;
    @Getter
    public String radio_href;
    @Getter
    public String radio_text;

    public Radio(JsonElement radio_id, JsonElement radio_class, JsonElement radio_href, JsonElement radio_link,WebDriver driver){
        this.driver = driver;
        if(radio_id!=null){
            this.radio_id = radio_id.getAsString();
        }
        if(radio_class!=null){
            this.radio_class = radio_class.getAsString();
        }
        if(radio_href!=null){
            this.radio_href = radio_href.getAsString();
        }
        if(radio_link!=null){
            this.radio_text = radio_link.getAsString();
        }
    }

    public String buildCssSelector(){
        String cssSelector = "";
        if (radio_id!=null){
            cssSelector += "#" + radio_id;
        }
        if (radio_href!=null){
            cssSelector += "[href='" + radio_href +"']";
        }
        //will be at the end also need to spilt base on space and dots
        if (radio_class!=null){
            cssSelector += "." + radio_class.replace(" ",".");
        }
        return cssSelector;
    }

    //return xpathSelector locator based on the field values
    public String buildXpathSelector(){
        String xpathSelector = "//*";
        if (radio_id!=null){
            xpathSelector += "[@id='" + radio_id + "']";
        }
        if (radio_class!=null){
            xpathSelector += "[@class='" + radio_class + "']";
        }
        if (radio_href!=null){
            xpathSelector += "[@href='" + radio_href + "']";
        }
        if (radio_text!=null && removeTextFromXpath.equals(false)) {
            xpathSelector += "[text()='" + radio_text +"']";
        }
        return xpathSelector;
    }

    public WebElement getradioWebElement() {
        if (radio_id!=null){
            List<WebElement> elements = driver.findElements(By.id(getRadio_id()));
            if (elements.size()==1){
                return elements.get(0);
            }
        }
        else {
            if (radio_text!=null){
                try{
                    return driver.findElement(By.xpath(buildXpathSelector()));
                }
                catch (Exception e){
                    if (e.getMessage().contains("Unable to locate element: {\"method\":\"xpath\",\"selector\":\""+buildXpathSelector()+"\"}")){
                        removeTextFromXpath = true;
                        List<WebElement> elements=driver.findElements(By.xpath(buildXpathSelector()));
                        for (WebElement ele: elements){
                            if (ele.getText().equals(radio_text)){
                                return ele;
                            }
                        }
                        throw new NoSuchElementException("radio with text:"+radio_text+" not found");
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
