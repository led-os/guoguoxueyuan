package com.test720.grasshoppercollege.bean;

/**
 * Created by 水东流 on 2018/4/16.
 */

public class BaseNameValue implements NameValue {
    String name;
    String value;

    public BaseNameValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }
}
