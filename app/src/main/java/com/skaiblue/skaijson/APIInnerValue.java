package com.skaiblue.skaijson;

import java.util.List;

public class APIInnerValue {

    @JSONField
    Integer id;

    @JSONField
    String joke;

    @JSONField
    List<String> categories;

    public APIInnerValue()
    {

    }

    @Override
    public String toString() {
        return "APIInnerValue{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                ", categories=" + categories +
                '}';
    }
}
