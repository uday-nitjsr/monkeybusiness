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
public class Textbox {
    public WebDriver driver;
    public Boolean removeTextFromXpath = false;
    Boolean isRelTextPresent = false;
    @Getter
    public String textbox_id;
    @Getter
    public String textbox_class;
    @Getter
    public String textbox_href;
    @Getter
    public String textbox_text;
    @Getter
    public String textbox_reltext;

    //need to add placeholder

    public Textbox(JsonElement textbox_id, JsonElement textbox_class, JsonElement textbox_href, JsonElement textbox_link,JsonElement textbox_reltext,WebDriver driver){
        this.driver = driver;
        if(textbox_id!=null){
            this.textbox_id = textbox_id.getAsString();
        }
        if(textbox_class!=null){
            this.textbox_class = textbox_class.getAsString();
        }
        if(textbox_href!=null){
            this.textbox_href = textbox_href.getAsString();
        }
        if(textbox_link!=null){
            this.textbox_text = textbox_link.getAsString();
        }
        if (textbox_reltext!=null){
            isRelTextPresent = true;
            this.textbox_reltext = textbox_reltext.getAsString();
        }
    }

    //returns cssSelector locator based on the field values
    public String buildCssSelector(){
        String cssSelector = "";
        if (textbox_id!=null){
            cssSelector += "#" + textbox_id;
        }
        if (textbox_href!=null){
            cssSelector += "[href='" + textbox_href +"']";
        }
        //will be at the end also need to spilt base on space and dots
        if (textbox_class!=null){
            cssSelector += "." + textbox_class.replace(" ",".");
        }
        return cssSelector;
    }

    //return xpathSelector locator based on the field values
    public String buildXpathSelector(){
        String xpathSelector;
        if (isRelTextPresent){
            xpathSelector = "//*[text()='"+textbox_reltext+"']/..//*";
        }
        else {
            xpathSelector = "//*";
        }
        if (textbox_id!=null){
            xpathSelector += "[@id='" + textbox_id + "']";
        }
        if (textbox_class!=null){
            xpathSelector += "[@class='" + textbox_class + "']";
        }
        if (textbox_href!=null){
            xpathSelector += "[@href='" + textbox_href + "']";
        }
        if (textbox_text!=null && removeTextFromXpath.equals(false)) {
            xpathSelector += "[text()='" + textbox_text +"']";
        }
        return xpathSelector;
    }

    public WebElement gettextboxWebElement() {
        if (textbox_id!=null){
            List<WebElement> elements = driver.findElements(By.id(getTextbox_id()));
            if (elements.size()==1){
                return elements.get(0);
            }
        }
        else {
            if (textbox_text!=null){
                try{
                    return driver.findElement(By.xpath(buildXpathSelector()));
                }
                catch (Exception e){
                    if (e.getMessage().contains("Unable to locate element: {\"method\":\"xpath\",\"selector\":\""+buildXpathSelector()+"\"}")){
                        removeTextFromXpath = true;
                        List<WebElement> elements=driver.findElements(By.xpath(buildXpathSelector()));
                        for (WebElement ele: elements){
                            if (ele.getText().equals(textbox_text)){
                                return ele;
                            }
                        }
                        throw new NoSuchElementException("textbox with text:"+textbox_text+" not found");
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
