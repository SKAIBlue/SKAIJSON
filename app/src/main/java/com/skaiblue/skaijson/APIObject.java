package com.skaiblue.skaijson;

public class APIObject {

    //@JSONField
    String type;

    //@JSONField
    APIInnerValue value;

    public APIObject()
    {

    }

    @Override
    public String toString() {
        return "APIObject{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }

}
