package com.tal.Base.Element;

import com.google.gson.JsonElement;
import lombok.Getter;

/**
 * Created by asus on 5/18/2017.
 */
public class CheckBox {
    @Getter
    public String checkbox_id;
    @Getter
    public String checkbox_class;
    @Getter
    public String checkbox_href;
    @Getter
    public String checkbox_link;

    public CheckBox(JsonElement checkbox_id, JsonElement checkbox_class, JsonElement checkbox_href, JsonElement checkbox_link){
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
            this.checkbox_link = checkbox_link.getAsString();
        }
    }
}
