package com.giftm.google1.rxtest;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("v2/5dfb13f72f00006200ff9be6/")
    Observable<APIData> getData();
}
