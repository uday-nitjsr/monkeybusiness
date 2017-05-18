package com.tal.Base.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tal.Base.Element.Button;
import com.tal.Base.Element.Radio;
import com.tal.Base.Element.enums.ElementType;
import net.sourceforge.htmlunit.corejs.javascript.ObjToIntMap;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by asus on 5/17/2017.
 */
public class ParseJson {
    public HashMap<String,Object> hashMap = new HashMap<String, Object>();

    public String jsonString = "{\n" +
            " \"Link_Topnav\": [\n" +
            "  {\n" +
            "   \"name\": \"Flights\",\n" +
            "   \"url\": \"https://www.goibibo.com/flights/\",\n" +
            "   \"class\": \"iconText \"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Hotels\",\n" +
            "   \"url\": \"https://www.goibibo.com/hotels/\",\n" +
            "   \"class\": \"iconText \"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Certified Stays\",\n" +
            "   \"url\": \"https://www.goibibo.com/gostays/\",\n" +
            "   \"class\": \"iconText \"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Bus\",\n" +
            "   \"url\": \"https://www.goibibo.com/bus/\",\n" +
            "   \"class\": \"iconText \"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Flight + Hotels\",\n" +
            "   \"url\": \"https://www.goibibo.com/go/f/\",\n" +
            "   \"class\": \"iconText\"\n" +
            "  }\n" +
            " ],\n" +
            " \"RadioButton_BookingType\": [\n" +
            "  {\n" +
            "   \"name\": \"Round Trip\",\n" +
            "   \"class\": \"white radio\",\n" +
            "   \"for\": \"gi_roundtrip_label\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"One Way\",\n" +
            "   \"class\": \"white radio\",\n" +
            "   \"for\": \"gi_oneway_label\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Multi City\",\n" +
            "   \"class\": \"white radio\",\n" +
            "   \"for\": \"gi_multicity_label\"\n" +
            "  }\n" +
            " ],\n" +
            " \"Label\": [\n" +
            "  {\n" +
            "   \"name\": \"Depart:\",\n" +
            "   \"class\": \"form-control inputTxtLarge widgetCalenderTxt\",\n" +
            "   \"val\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Return:\",\n" +
            "   \"class\": \"form-control inputTxtLarge widgetCalenderTxt\",\n" +
            "   \"val\": \"\"\n" +
            "  }\n" +
            " ],\n" +
            " \"selection1\": [\n" +
            "  {\n" +
            "  },\n" +
            "  {\n" +
            "   \"Options\": [\n" +
            "    {\n" +
            "     \"text\": \"Economy\",\n" +
            "     \"value\": \"E\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"text\": \"Business\",\n" +
            "     \"value\": \"B\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"text\": \"First class\",\n" +
            "     \"value\": \"F\"\n" +
            "    },\n" +
            "    {\n" +
            "     \"text\": \"Premium Economy\",\n" +
            "     \"value\": \"W\"\n" +
            "    }\n" +
            "   ]\n" +
            "  }\n" +
            " ],\n" +
            " \"Button\": [\n" +
            "  {\n" +
            "   \"name\": \"Get Set Go\",\n" +
            "   \"id\": \"gi_search_btn\",\n" +
            "   \"class\": \"width100 button orange xlarge\"\n" +
            "  },\n" +
            "  {\n" +
            "   \"name\": \"Flight + Hotels\",\n" +
            "   \"id\": \"gi_search_fph_btn\",\n" +
            "   \"class\": \"width100 button xlarge btnBackDark\"\n" +
            "  }\n" +
            " ],\n" +
            " \"Checkbox\":[{\n" +
            " \"name\": \"Check some\",\n" +
            " \"id\": \"someting\"\n" +
            " }\n" +
            " ]\n" +
            "}";
    JSONObject obj = new JSONObject(jsonString);

    public void parseThis(JSONObject jsonObject, Boolean isWebElement, ElementType type) throws Exception {
        if (isWebElement){
            switch(type){
                case BUTTON:
                    //pass json object to create Button element
                    break;
                case CHECKBOX:
                    //pass json object to create Checkbox element
                    break;
                case DROPDOWN:
                    //pass json object to create Dropdown element
                    break;
                case LINK:
                    //pass json object to create Link element
                    break;
                case RADIOBUTTON:
                    //pass json object to create Radio Button element
                    break;
                case TEXTBOX:
                    //pass json object to create Textbox element
                    break;
            }
        }
        else {
            //Iterate jsonObject to check how many types of Element are present and create a stringlist
//            JSONArray jsonArray = jsonObject.getJSONArray("");
            Iterator keysToInterate = jsonObject.keys();
            List<String> keyList = new ArrayList<String>();
            while (keysToInterate.hasNext()){
                String key = (String) keysToInterate.next();
                keyList.add(key);
            }
            //pass this list to checkElementListFromJson to compare it against the existing ElementType
            checkElementListFromJson(keyList);
        }
    }

    public void checkElementListFromJson(List<String> keysList){
        for (String e:keysList){
            Boolean found = false;
            ElementType elementType=null;
            for (ElementType j:ElementType.values()){
                if (e.contains(j.toString())){
                    found = true;
                    elementType = j;
                    break;
                }
            }
            if (!found){
                //Add to report e is not found in ElementType
            }
            else {
                JSONArray jsonArray = obj.getJSONArray(e);
                //Create MasterElement Hasmap with all objects
                fillHashMap(hashMap,elementType,e);

            }
        }
    }

    public void fillHashMap(HashMap hashMap,ElementType element,String elementName){
        switch (element){
            case TEXTBOX:
                break;
            case RADIOBUTTON:
                break;
            case LINK:
                break;
            case DROPDOWN:
                break;
            case BUTTON:
                //getElementList
                hashMap.put(elementName,getButtonList(elementName));
                break;
            case CHECKBOX:
                break;
        }
    }

    public List getButtonList(String elementKeyName){
        List<Button> buttons= new ArrayList<Button>();
        JSONArray jsonArray = obj.getJSONArray(elementKeyName);
        for (int i=0;i<jsonArray.length();i++){
            buttons.add(new Button(jsonArray.getJSONObject(i).getString("name"),
                    jsonArray.getJSONObject(i).getString("class"),
                    jsonArray.getJSONObject(i).getString("href"),
                    jsonArray.getJSONObject(i).getString("link")));
        }
        return buttons;
    }

    public List getRadioList(String elementKeyName){
        List<Radio> radios = new ArrayList<Radio>();
        JSONArray jsonArray = obj.getJSONArray(elementKeyName);
        for (int i=0;i<jsonArray.length();i++){
            radios.add(new Radio());
        }
        return radios;
    }

    public static void main(String args[]) throws Exception {
        ParseJson parseJson = new ParseJson();
//        System.out.println(parseJson.parseThis(parseJson.obj,false,null));
    }
}
