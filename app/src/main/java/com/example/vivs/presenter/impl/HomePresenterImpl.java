package com.example.vivs.presenter.impl;

import com.example.vivs.presenter.Interface.IHomePresenter;
import com.example.vivs.view.Interface.IHomeCallback;

public class HomePresenterImpl implements IHomePresenter {

private IHomeCallback mCallback;
    @Override
    public void registerViewCallback(IHomeCallback callback) {
        this.mCallback=callback;
    }

    @Override
    public void unregisterViewCallback(IHomeCallback callback) {

    }
}
