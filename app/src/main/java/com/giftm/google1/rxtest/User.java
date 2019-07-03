package com.giftm.google1.rxtest;

import lombok.Getter;

public class User {


    public User(String  type, String type2) {
        this.type = type;
        this.type2 = type2;
    }

    @Getter
    String  type;
    @Getter
    String type2;
}
