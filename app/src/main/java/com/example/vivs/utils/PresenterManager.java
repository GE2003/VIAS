package com.example.vivs.utils;

import com.example.vivs.presenter.impl.NewsPresenterImpl;

public class PresenterManager {
    private static final PresenterManager ourInstance = new PresenterManager();
    private final NewsPresenterImpl homePresenter;

    public NewsPresenterImpl getHomePresenter() {
        return homePresenter;
    }

    public static PresenterManager getInstance(){
        return ourInstance;
    }
    private PresenterManager(){
        //在这里创建presenter
        homePresenter = new NewsPresenterImpl();
    }
}
