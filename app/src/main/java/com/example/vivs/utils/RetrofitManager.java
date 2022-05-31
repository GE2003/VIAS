package com.example.vivs.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final RetrofitManager ourInstance=new RetrofitManager();
    private final Retrofit mRetrofit;
    public static   String BASEURL;
    public static RetrofitManager getInstance(String BaseURl){
        BASEURL=BaseURl;
        return ourInstance;
    }
    private RetrofitManager(){
        //创建一个retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_TEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public Retrofit getRetrofit(){

        return mRetrofit;
    }
}
