package com.tal.Base.Element.enums;

public enum ElementType{
    BUTTON      ("Button"),
    TEXTBOX     ("Textbox"),
    LINK        ("Link"),
    CHECKBOX    ("Checkbox"),
    RADIOBUTTON ("RadioButton"),
    DROPDOWN    ("Dropdown");

    private String _label;
    ElementType(String lable){
        _label = lable;
    }
    public String toString() {
        return _label;
    }
}