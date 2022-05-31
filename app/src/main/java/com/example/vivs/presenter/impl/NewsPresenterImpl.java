package com.example.vivs.presenter.impl;

import android.util.Log;
import com.example.vivs.model.domin.Category;
import com.example.vivs.presenter.Interface.IHomePresenter;
import com.example.vivs.view.Interface.INewsCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsPresenterImpl implements IHomePresenter {

    private static final String TAG = "NewsPresenterImpl";
    private INewsCallback mCallback;
    @Override
    public void registerViewCallback(INewsCallback callback) {

        this.mCallback=callback;
    }

    @Override
    public void unregisterViewCallback(INewsCallback callback) {
      mCallback=null;
    }

    @Override
    public void getCategories() {
        if (mCallback!=null){
            mCallback.onLoading();
        }
        List<Category> data = new ArrayList<>();
        Map<Integer,String> map = new HashMap<>();
        Log.d(TAG,"走一次---------------------");
        map.put(1,"社会");
        map.put(2,"科技");
        map.put(3,"房产");
        map.put(4,"财经");
        map.put(5,"时尚");
        map.put(6,"游戏");
        map.put(7,"体育");
        map.put(8,"时政");
        map.put(9,"教育");
        map.put(10,"娱乐");
        for (int i = 1; i <=10; i++) {
          Category category = new Category();
            category.setId(i);
            category.setTitle(map.get(i));
            data.add(category);
        }
     mCallback.onCategoriesLoaded(data);


    }
}
