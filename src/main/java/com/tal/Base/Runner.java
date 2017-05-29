package com.tal.Base;

import com.tal.Base.Element.*;
import com.tal.Base.Element.enums.ElementType;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by asus on 5/24/2017.
 */
public class Runner extends MasterRunner {

    @Test
    public void run() {
        Iterator iterator = parseJson.hashMap.entrySet().iterator();
        while (iterator.hasNext()){
            HashMap.Entry pair = (HashMap.Entry)iterator.next();
            String pairKeyString  = pair.getKey().toString();
            if (pairKeyString.split("_")[0].equals(ElementType.BUTTON.toString())){
                //create a button list
                List<Button> buttons = (List<Button>) parseJson.hashMap.get(pairKeyString);
                //perform all actions
                performAllButtonActions(buttons,pairKeyString);
            }
            else if (pairKeyString.split("_")[0].equals(ElementType.CHECKBOX.toString())){
                List<CheckBox> checkBoxes = (List<CheckBox>) parseJson.hashMap.get(pairKeyString);
                performAllCheckboxActions(checkBoxes,pairKeyString);
            }
            else if (pairKeyString.split("_")[0].equals(ElementType.DROPDOWN)){
                List<Dropdown> dropdowns = (List<Dropdown>) parseJson.hashMap.get(pairKeyString);
                //performAllDropdownActions(dropdowns,pairKeyString);
            }
            else if (pairKeyString.split("_")[0].equals(ElementType.LINK)){

            }
            else if (pairKeyString.split("_")[0].equals(ElementType.RADIOBUTTON.toString())){
                List<Radio> radios = (List<Radio>) parseJson.hashMap.get(pairKeyString);
                performAllRadioActions(radios,pairKeyString);
            }
            else if (pairKeyString.split("_")[0].equals(ElementType.TEXTBOX.toString())){
                List<Textbox> textboxes = (List<Textbox>) parseJson.hashMap.get(pairKeyString);
                peformAllTextboxActions(textboxes,pairKeyString);
            }
        }
    }

}
