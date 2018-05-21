package com.skaiblue.skaijson.data;

import com.skaiblue.skaijson.JSONField;

public class JLogin {

    public JLogin() {
    }

    @JSONField
    String username;

    @JSONField
    String password;


    @Override
    public String toString() {
        return "JLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
