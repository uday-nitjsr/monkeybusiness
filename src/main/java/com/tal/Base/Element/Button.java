package com.tal.Base.Element;

import com.google.gson.JsonElement;
import lombok.Getter;

/**
 * Created by asus on 5/17/2017.
 */
public class Button {

    @Getter
    public String button_id;
    @Getter
    public String button_class;
    @Getter
    public String button_href;
    @Getter
    public String button_link;

    public Button(JsonElement button_id, JsonElement button_class, JsonElement button_href, JsonElement button_link){
        if(button_id!=null){
            this.button_id = button_id.getAsString();
        }
        if(button_class!=null){
            this.button_class = button_class.getAsString();
        }
        if(button_href!=null){
            this.button_href = button_href.getAsString();
        }
        if(button_link!=null){
            this.button_link = button_link.getAsString();
        }
    }

}
