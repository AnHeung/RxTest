package com.giftm.google1.rxtest;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;

public class Data {

    public Observable<List<Integer>> getArrays(){

        List list = new ArrayList();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(4);

        return Observable.just(list);
    }

    public Observable<List<Integer>> getArraysDefer(){

        return Observable.defer(() -> {

            List<Integer> list = new ArrayList();
            list.add(1);
            list.add(3);
            list.add(2);
            list.add(5);
            list.add(4);

            return Observable.just(list);
        });
    }

    public Observable<List<Integer>> getArrayFromCallable(){

        return Observable.fromCallable(() -> {
            List<Integer> list = new ArrayList();
            list.add(1);
            list.add(3);
            list.add(2);
            list.add(5);
            list.add(4);
            return list;
        });
    }

    public Observable<List<User>> getUser(){

        return Observable.fromCallable(() -> {
            List<User> list = new ArrayList();
            list.add(new User("1","2"));
            list.add(new User("11","22"));
            list.add(new User("3","4"));
            list.add(new User("22","5"));
            list.add(new User("0","6"));
            return list;
        });
    }
}
