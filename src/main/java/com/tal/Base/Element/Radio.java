package com.tal.Base.Element;

import com.google.gson.JsonElement;
import lombok.Getter;

/**
 * Created by asus on 5/18/2017.
 */
public class Radio {
    @Getter
    public String radio_id;
    @Getter
    public String radio_class;
    @Getter
    public String radio_href;
    @Getter
    public String radio_link;

    public Radio(JsonElement radio_id, JsonElement radio_class, JsonElement radio_href, JsonElement radio_link){
        if(radio_id!=null){
            this.radio_id = radio_id.getAsString();
        }
        if(radio_class!=null){
            this.radio_class = radio_class.getAsString();
        }
        if(radio_href!=null){
            this.radio_href = radio_href.getAsString();
        }
        if(radio_link!=null){
            this.radio_link = radio_link.getAsString();
        }
    }
}
