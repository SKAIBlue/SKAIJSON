package com.skaiblue.skaijson.data;

import com.skaiblue.skaijson.JSONField;

public class JLocation {

    public JLocation() {
    }

    @JSONField
    String street;

    @JSONField
    String city;

    @JSONField
    String state;

    @JSONField
    Integer postcode;

    @Override
    public String toString() {
        return "JLocation{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postcode=" + postcode +
                '}';
    }
}
