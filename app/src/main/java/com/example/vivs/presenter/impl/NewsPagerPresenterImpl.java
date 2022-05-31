package com.example.vivs.presenter.impl;

import android.util.Log;
import com.example.vivs.model.domin.NewsData;
import com.example.vivs.model.domin.news;
import com.example.vivs.presenter.Interface.INewsPagerPresenter;
import com.example.vivs.utils.API;
import com.example.vivs.utils.Constants;
import com.example.vivs.utils.RetrofitManager;
import com.example.vivs.utils.URLUtils;
import com.example.vivs.view.Interface.INewsPagerCallBack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsPagerPresenterImpl implements INewsPagerPresenter {
    private static final String TAG = "NewsPagerPresenterImpl";
public NewsPagerPresenterImpl (){}
private static NewsPagerPresenterImpl sInstance=null;
public static NewsPagerPresenterImpl getInstance(){
    if (sInstance==null){
        sInstance=new NewsPagerPresenterImpl();
    }
    return sInstance;
}


    @Override
    public void getTitleByNewsModuleId(int NewsModuleId) {

               Log.d(TAG,"NEWSMODUleid-----------"+NewsModuleId);
        for (INewsPagerCallBack mCallback : callbacks) {


            if (mCallback.getCategoriessId()==NewsModuleId){
               Log.d(TAG,"callback-----------"+mCallback.getCategoriessId());
               Log.d(TAG,"NEWSMODUleid-----------"+NewsModuleId);
                mCallback.onLoading(NewsModuleId);
            }
        }

        Call<NewsData> task = getTask(NewsModuleId, 40, 1);
        task.enqueue(new Callback<NewsData>() {
            @Override
            public void onResponse(Call<NewsData> call, Response<NewsData> response) {
                if (response.code()== HttpURLConnection.HTTP_OK){
                    Log.d("NewsData------------","body------"+response.body().getData().getRecords().toString());

                    ArrayList<news> records = response.body().getData().getRecords();
              handleNewsContentRes(NewsModuleId,records);


                }
            }

            @Override
            public void onFailure(Call<NewsData> call, Throwable t) {
              Log.d("NewsData------------","fail--------------"+t.toString());
            }
        });
    }

    private void handleNewsContentRes(int newsModuleId, ArrayList<news> records) {
        for (INewsPagerCallBack mCallback : callbacks) {
            if (mCallback.getCategoriessId()==newsModuleId){

                if (records==null||records.size()==0){
                    mCallback.onError(newsModuleId);
                }else {

            mCallback.onNewsContentLoaded(records,newsModuleId);
                }

            }else {
/*
   Log.d(TAG,"id不对应--------------------");
   Log.d(TAG,"NESMODULEID-------------"+newsModuleId);
   Log.d(TAG,"callbackID------------"+mCallback.getCategoriessId());
*/
             //   mCallback.onLoading(newsModuleId);
            }
        }

    }

    @Override
    public void loaderMore(int NewsModuleId) {

    }

    @Override
    public void reload(int NewsModuleId) {

    }

    private Call<NewsData> getTask(int CategoryId,int PageSize,int PageNo){
        RetrofitManager retrofitManager = RetrofitManager.getInstance(Constants.BASE_TEST_URL);
        Retrofit retrofit = retrofitManager.getRetrofit();
        API api = retrofit.create(API.class);
        String url = URLUtils.GetCategoryNewsURL(Constants.BASE_TEST_URL, CategoryId, PageNo, PageSize);
              Log.d("NewsData------------","url--------------"+url);
        Call<NewsData> task = api.getNewsByCategoryId(url);
        return task;
    }


    private List<INewsPagerCallBack> callbacks = new ArrayList<>();

    @Override
    public void registerViewCallback(INewsPagerCallBack callback) {
              if (!callbacks.contains(callback)){
                  callbacks.add(callback);
              }
    }

    @Override
    public void unregisterViewCallback(INewsPagerCallBack callback) {

        callbacks.remove(callback);
    }


}
