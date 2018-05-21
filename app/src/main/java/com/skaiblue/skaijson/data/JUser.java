package com.skaiblue.skaijson.data;

import com.skaiblue.skaijson.JSONField;

public class JUser {

    // It must need default initializer for use this library.
    public JUser() { }

    @JSONField
    String gender;              // gender is String

    @JSONField
    JUserName name;             // name is JSONObject so you have to create class for it.

    @JSONField
    JLocation location;         // location is JSONObject so you have to create class for it.

    @JSONField
    String email;               // email is String

    @JSONField
    JLogin login;                // login is JSONObject so you have to create class for it.

    @JSONField
    String dob;

    @JSONField
    String registered;

    @JSONField
    String phone;

    @JSONField
    String cell;

    @JSONField
    JPicture picture;

    @JSONField
    String net;

    @Override
    public String toString() {
        return "JUser{" +
                "gender='" + gender + '\'' +
                ", name=" + name +
                ", location=" + location +
                ", email='" + email + '\'' +
                ", login=" + login +
                ", dob='" + dob + '\'' +
                ", registered='" + registered + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", picture=" + picture +
                ", net='" + net + '\'' +
                '}';
    }
}
