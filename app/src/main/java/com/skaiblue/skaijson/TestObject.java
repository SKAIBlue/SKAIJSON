package com.skaiblue.skaijson;

public class TestObject {

    @JSONField
    String str = "가나다라";

    @JSONField
    Integer value = 10;

    public TestObject() {

    }

    @Override
    public String toString() {
        return "TestObject{" +
                "str='" + str + '\'' +
                ", value=" + value +
                '}';
    }
}
