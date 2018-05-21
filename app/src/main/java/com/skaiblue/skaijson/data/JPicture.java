package com.skaiblue.skaijson.data;

import com.skaiblue.skaijson.JSONField;

public class JPicture {

    public JPicture() {
    }

    @JSONField
    String large;

    @JSONField
    String medium;

    @JSONField
    String thumbnail;


    @Override
    public String toString() {
        return "JPicture{" +
                "large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
