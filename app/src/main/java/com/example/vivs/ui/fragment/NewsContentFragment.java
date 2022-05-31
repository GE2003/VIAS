package com.example.vivs.ui.fragment;


import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.vivs.Base.BaseFragment;
import com.example.vivs.R;
import com.example.vivs.model.domin.Category;
import com.example.vivs.model.domin.news;
import com.example.vivs.presenter.Interface.INewsPagerPresenter;

import com.example.vivs.presenter.impl.NewsPagerPresenterImpl;

import com.example.vivs.view.Interface.INewsPagerCallBack;


import java.util.List;


public class NewsContentFragment extends BaseFragment implements  INewsPagerCallBack {
  public Context Newscontext;
    private static final String TAG = "NewsContentFragment";
    private INewsPagerPresenter newsPagerPresenter;

    public  static  String Res;

    private String title;
    private int id;

    public static NewsContentFragment newInstance(Category category){
    NewsContentFragment newsContentFragment = new NewsContentFragment();
    Bundle bundle = new Bundle();
    bundle.putInt("ID",category.getId());
    bundle.putString("title",category.getTitle());
    newsContentFragment.setArguments(bundle);
    return newsContentFragment;
}


public static NewsContentFragment getNewsFragment(String res){
        Res=res;

        Log.d(TAG,"res-----------"+res);

    return new NewsContentFragment();
}

public  void setSelectTabByres(String res){
       /* if (res==null||res.contains("推荐")){
    tabLayout.getTabAt(0).select();
        }else if(res.contains("热门")){
    tabLayout.getTabAt(1).select();
        }else if (res.contains("军事")){
    tabLayout.getTabAt(3).select();
        }else if (res.contains("科技")){
            tabLayout.getTabAt(4).select();
        }else if (res.contains("国际")){
            tabLayout.getTabAt(5).select();
        }else if (res.contains("财经")){
            tabLayout.getTabAt(6).select();
        }else if (res.contains("美食")){
            tabLayout.getTabAt(7).select();
        }else if (res.contains("情感")){
            tabLayout.getTabAt(8).select();
        }else if (res.contains("体育")){
            tabLayout.getTabAt(9).select();
        }else if (res.contains("时政")){
            tabLayout.getTabAt(10).select();
        }*/
}

    @Override
    protected void initPresenter() {
        newsPagerPresenter =  NewsPagerPresenterImpl.getInstance();
        newsPagerPresenter.registerViewCallback(this);
    }




    @Override
    protected void getFragmentContext(Context context) {
       this.Newscontext =context;

    }

    @Override
    protected void initview(View view) {

    }





    @Override
    protected void onClicklistener() {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_homepager;
    }

    @Override
    protected void LoadData() {
        Bundle arguments = getArguments();
        Log.d(TAG,"arguments---------"+arguments);
 if (arguments!=null){
     title = arguments.getString("title");
     id = arguments.getInt("ID");
 }
        if (newsPagerPresenter!=null){
        Log.d(TAG,"load data-----");
        newsPagerPresenter.getTitleByNewsModuleId(id);
    }
    }

    @Override
    public void onTitleLoaded() {

    }

    @Override
    public void onNewsContentLoaded(List<news> records, int NewsModuleID) {

    }





    @Override
    public int getNewsId() {
        return 0;
    }

    @Override
    public void onLoading(int NewsModuleId) {

    }

    @Override
    public void onError(int NewsModuleId) {

    }

    @Override
    public void onEmpty(int NewsModuleId) {

    }

    @Override
    public void onLoaderMoreError(int NewsModuleId) {

    }

    @Override
    public void onLoaderMoreEmpty(int NewsModuleId) {

    }

    @Override
    public void onLoaderMoreSuccess() {

    }
}
