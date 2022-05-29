package com.example.vivs.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final RetrofitManager ourInstance=new RetrofitManager();
    private final Retrofit mRetrofit;

    public static  RetrofitManager getInstance(){
        return ourInstance;
    }
    private RetrofitManager(){
        //创建一个retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_MOBILE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public Retrofit getRetrofit(){
        return mRetrofit;
    }
}
