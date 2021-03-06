package com.tal.Base.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tal.Base.Element.*;
import com.tal.Base.Element.enums.ElementType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.*;

/**
 * Created by asus on 5/17/2017.
 */
public class ParseJson {
    public WebDriver driver;
    public HashMap<String,Object> hashMap = new HashMap<String, Object>();
    public JsonObject obj;

    public void parseThis(JsonObject jsonObject) throws Exception {
        this.obj = jsonObject;
        //Iterate jsonObject to check how many types of Element are present and create a stringlist
        List<String> keyList = new ArrayList<String>();
        for (Map.Entry<String, JsonElement> e: obj.entrySet()){
            keyList.add(e.getKey());
        }
        //pass this list to checkElementListFromJson to compare it against the existing ElementType
        checkElementListFromJson(keyList);

    }

    public void checkElementListFromJson(List<String> keysList){
        for (String e:keysList){
            Boolean found = false;
            ElementType elementType=null;
            for (ElementType j:ElementType.values()){
                if (e.split("_")[0].equals(j.toString())){
                    found = true;
                    elementType = j;
                    break;
                }
            }
            if (!found){
                //Add to report e is not found in ElementType
            }
            else {
                fillHashMap(hashMap,elementType,e);
            }
        }
    }

        public void fillHashMap(HashMap hashMap,ElementType element,String elementName){
        switch (element){
            case TEXTBOX:
                hashMap.put(elementName,getTextbox(elementName));
                break;
            case RADIOBUTTON:
                hashMap.put(elementName,getRadioList(elementName));
                break;
            case LINK:
                break;
            case DROPDOWN:
                hashMap.put(elementName,getDropdownList(elementName));
                break;
            case BUTTON:
                //getElementList
                hashMap.put(elementName,getButtonList(elementName));
                break;
            case CHECKBOX:
                hashMap.put(elementName,getCheckboxList(elementName));
                break;
        }
    }

    public List getButtonList(String elementKeyName){
        List<Button> buttons= new ArrayList<Button>();
        JsonArray jsonArray = obj.getAsJsonArray(elementKeyName);
        for (int i=0;i<jsonArray.size();i++){
            buttons.add(new Button(jsonArray.get(i).getAsJsonObject().get("id"),
                    jsonArray.get(i).getAsJsonObject().get("class"),
                    jsonArray.get(i).getAsJsonObject().get("href"),
                    jsonArray.get(i).getAsJsonObject().get("text"),
                    jsonArray.get(i).getAsJsonObject().get("reltext"),this.driver));
        }
        return buttons;
    }

    public List getRadioList(String elementKeyName){
        List<Radio> radios = new ArrayList<Radio>();
        JsonArray jsonArray = obj.getAsJsonArray(elementKeyName);
        for (int i=0;i<jsonArray.size();i++){
            radios.add(new Radio(jsonArray.get(i).getAsJsonObject().get("name"),
                    jsonArray.get(i).getAsJsonObject().get("class"),
                    jsonArray.get(i).getAsJsonObject().get("href"),
                    jsonArray.get(i).getAsJsonObject().get("text"),driver));
        }
        //same logic as getButtonList
        return radios;
    }

    public List getCheckboxList(String elementKeyName){
        List<CheckBox> checkBoxes = new ArrayList<CheckBox>();
        JsonArray jsonArray = obj.getAsJsonArray(elementKeyName);
        for (int i=0;i<jsonArray.size();i++){
            checkBoxes.add(new CheckBox(jsonArray.get(i).getAsJsonObject().get("name"),
                    jsonArray.get(i).getAsJsonObject().get("class"),
                    jsonArray.get(i).getAsJsonObject().get("href"),
                    jsonArray.get(i).getAsJsonObject().get("text"),driver));
        }
        return checkBoxes;
    }

    public List getTextbox(String elementKeyName){
        List<Textbox> textboxes = new ArrayList<Textbox>();
        JsonArray jsonArray = obj.getAsJsonArray(elementKeyName);
        for (int i=0;i<jsonArray.size();i++){
            textboxes.add(new Textbox(jsonArray.get(i).getAsJsonObject().get("name"),
                    jsonArray.get(i).getAsJsonObject().get("class"),
                    jsonArray.get(i).getAsJsonObject().get("href"),
                    jsonArray.get(i).getAsJsonObject().get("text"),
                    jsonArray.get(i).getAsJsonObject().get("reltext"),driver));
        }
        return textboxes;
    }

    public List getDropdownList(String elementKeyName){
        List<Dropdown> dropdowns = new ArrayList<Dropdown>();
        JsonArray jsonArray = obj.getAsJsonArray(elementKeyName);
        for (int i=0;i<jsonArray.size();i++){
            dropdowns.add(new Dropdown(jsonArray.get(i).getAsJsonObject().get("name"),
                    jsonArray.get(i).getAsJsonObject().get("class"),
                    jsonArray.get(i).getAsJsonObject().get("href"),
                    jsonArray.get(i).getAsJsonObject().get("text"),driver));
        }
        return dropdowns;
    }

    public ParseJson(WebDriver driver){
        this.driver = driver;
    }

}
