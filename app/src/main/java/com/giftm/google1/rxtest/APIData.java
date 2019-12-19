package com.giftm.google1.rxtest;

import com.google.gson.annotations.SerializedName;

public class APIData {

    @SerializedName("test")
    public String test;


    @SerializedName("val")
    public String val;

    public String getTest() {
        return test;
    }

    public String getVal() {
        return val;
    }
}
