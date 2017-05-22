package com.tal.Base.Element;

import com.google.gson.JsonElement;
import lombok.Getter;

/**
 * Created by asus on 5/18/2017.
 */
public class Textbox {
    @Getter
    public String textbox_id;
    @Getter
    public String textbox_class;
    @Getter
    public String textbox_href;
    @Getter
    public String textbox_link;

    public Textbox(JsonElement textbox_id, JsonElement textbox_class, JsonElement textbox_href, JsonElement textbox_link){
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
            this.textbox_link = textbox_link.getAsString();
        }
    }
}
