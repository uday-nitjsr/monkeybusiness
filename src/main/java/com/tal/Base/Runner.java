package com.tal.Base;

import com.tal.Base.Element.Button;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by asus on 5/24/2017.
 */
public class Runner extends MasterRunner {

    @Test
    public void run(){
        List<Button> buttons = (List<Button>) parseJson.hashMap.get("Button");
        for (Button button:buttons){
            System.out.println("Class:"+button.button_class+" ID:"+button.button_id
                    +" Href:"+button.button_href+" Text:"+button.button_text);
            try {
                button.getButtonWebElement().click();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
