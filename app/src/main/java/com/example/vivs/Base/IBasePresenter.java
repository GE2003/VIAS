package com.example.vivs.Base;

public interface IBasePresenter<T> {
    //注册接口
    void registerViewCallback(T callback);
    void unregisterViewCallback(T callback);
}
