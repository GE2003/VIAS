package com.example.vivs.utils;

import com.example.vivs.presenter.impl.HomePresenterImpl;

public class PresenterManager {
    private static final PresenterManager ourInstance = new PresenterManager();
    private final HomePresenterImpl homePresenter;

    public HomePresenterImpl getHomePresenter() {
        return homePresenter;
    }

    public static PresenterManager getInstance(){
        return ourInstance;
    }
    private PresenterManager(){
        //在这里创建presenter
        homePresenter = new HomePresenterImpl();
    }
}
