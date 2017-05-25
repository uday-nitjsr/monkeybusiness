package com.tal.Base;

import com.tal.Base.Element.Button;
import com.tal.Base.Element.CheckBox;
import com.tal.Base.Element.Radio;
import com.tal.Base.Element.enums.ElementType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by asus on 5/24/2017.
 */
public class Runner extends MasterRunner {

    @Test
    public void run(){
        Iterator iterator = parseJson.hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            HashMap.Entry pair = (HashMap.Entry)iterator.next();
            String pairKeyString  = pair.getKey().toString();
            if (pairKeyString.split("_")[0].equals(ElementType.BUTTON.toString())){
                //create a button list
                List<Button> buttons = (List<Button>) parseJson.hashMap.get(pairKeyString);
                //perform all actions
                performAllButtonActions(buttons);
            }
            else if (pairKeyString.split("_")[0].equals(ElementType.CHECKBOX.toString())){

            }
            else if (pairKeyString.equals(ElementType.DROPDOWN)){

            }
            else if (pairKeyString.equals(ElementType.LINK)){

            }
            else if (pairKeyString.split("_")[0].equals(ElementType.RADIOBUTTON.toString())){
                List<Radio> radios = (List<Radio>) parseJson.hashMap.get(pairKeyString);
                for (Radio radio:radios){
                    System.out.println("Class:"+radio.radio_class+" ID:"+radio.radio_id
                            +" Href:"+radio.radio_href+" Text:"+radio.radio_text);
                    try {
                        radio.getradioWebElement().click();
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            else if (pairKeyString.equals(ElementType.TEXTBOX)){

            }
            else {

            }
        }
    }

}
