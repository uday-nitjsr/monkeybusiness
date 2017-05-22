package com.tal.Base.Element;

import com.google.gson.JsonElement;
import lombok.Getter;

/**
 * Created by asus on 5/18/2017.
 */
public class Dropdown {
    @Getter
    public String dropdown_id;
    @Getter
    public String dropdown_class;
    @Getter
    public String dropdown_href;
    @Getter
    public String dropdown_link;

    public Dropdown(JsonElement dropdown_id, JsonElement dropdown_class, JsonElement dropdown_href, JsonElement dropdown_link){
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
            this.dropdown_link = dropdown_link.getAsString();
        }
    }
}
