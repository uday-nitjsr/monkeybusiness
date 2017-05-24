package com.tal.Base.Element.enums;

/**
 * Created by asus on 5/23/2017.
 */
public enum AttributeType {
    ID      ("id"),
    CLASS   ("class"),
    HREF    ("href"),
    LINK    ("link");

    private String _label;
    AttributeType(String label){
        _label = label;
    }
}
