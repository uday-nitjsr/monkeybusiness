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
public class CheckBox {
    public WebDriver driver;
    private Boolean removeTextFromXpath = false;
    @Getter
    public String checkbox_id;
    @Getter
    public String checkbox_class;
    @Getter
    public String checkbox_href;
    @Getter
    public String checkbox_text;

    public CheckBox(JsonElement checkbox_id, JsonElement checkbox_class, JsonElement checkbox_href, JsonElement checkbox_link,WebDriver driver){
        this.driver = driver;
        if(checkbox_id!=null){
            this.checkbox_id = checkbox_id.getAsString();
        }
        if(checkbox_class!=null){
            this.checkbox_class = checkbox_class.getAsString();
        }
        if(checkbox_href!=null){
            this.checkbox_href = checkbox_href.getAsString();
        }
        if(checkbox_link!=null){
            this.checkbox_text = checkbox_link.getAsString();
        }
    }

    //returns cssSelector locator based on the field values
    public String buildCssSelector(){
        String cssSelector = "";
        if (checkbox_id!=null){
            cssSelector += "#" + checkbox_id;
        }
        if (checkbox_href!=null){
            cssSelector += "[href='" + checkbox_href +"']";
        }
        //will be at the end also need to spilt base on space and dots
        if (checkbox_class!=null){
            cssSelector += "." + checkbox_class.replace(" ",".");
        }
        return cssSelector;
    }

    //return xpathSelector locator based on the field values
    public String buildXpathSelector(){
        String xpathSelector = "//*";
        if (checkbox_id!=null){
            xpathSelector += "[@id='" + checkbox_id + "']";
        }
        if (checkbox_class!=null){
            xpathSelector += "[@class='" + checkbox_class + "']";
        }
        if (checkbox_href!=null){
            xpathSelector += "[@href='" + checkbox_href + "']";
        }
        if (checkbox_text!=null && removeTextFromXpath.equals(false)) {
            xpathSelector += "[text()='" + checkbox_text +"']";
        }
        return xpathSelector;
    }

    public WebElement getcheckboxWebElement() {
        if (checkbox_id!=null){
            List<WebElement> elements = driver.findElements(By.id(getCheckbox_id()));
            if (elements.size()==1){
                return elements.get(0);
            }
        }
        else {
            if (checkbox_text!=null){
                try{
                    return driver.findElement(By.xpath(buildXpathSelector()));
                }
                catch (Exception e){
                    if (e.getMessage().contains("Unable to locate element: {\"method\":\"xpath\",\"selector\":\""+buildXpathSelector()+"\"}")){
                        removeTextFromXpath = true;
                        List<WebElement> elements=driver.findElements(By.xpath(buildXpathSelector()));
                        for (WebElement ele: elements){
                            if (ele.getText().equals(checkbox_text)){
                                return ele;
                            }
                        }
                        throw new NoSuchElementException("checkbox with text:"+checkbox_text+" not found");
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
