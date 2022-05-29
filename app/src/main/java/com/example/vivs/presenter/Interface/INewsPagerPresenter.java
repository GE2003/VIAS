package com.example.vivs.presenter.Interface;

import com.example.vivs.Base.IBasePresenter;
import com.example.vivs.view.Interface.IHomeCallback;
import com.example.vivs.view.Interface.INewsPagerCallBack;
import com.example.vivs.view.Interface.ISpeakCallBack;

public interface INewsPagerPresenter extends IBasePresenter<INewsPagerCallBack> {
    void getTitleByNewsModuleId(int NewsModuleId);
    void loaderMore(int NewsModuleId);
    void reload(int NewsModuleId);
    void registerViewCallback(INewsPagerCallBack callback);
    void unregisterViewCallback(INewsPagerCallBack callback);

}
