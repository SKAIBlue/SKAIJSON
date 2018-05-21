package com.skaiblue.skaijson.data;

import com.skaiblue.skaijson.JSONField;

public class JUserName {
    public JUserName() {}

    @JSONField
    String title;

    @JSONField
    String first;

    @JSONField
    String last;


    @Override
    public String toString() {
        return "JUserName{" +
                "title='" + title + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
