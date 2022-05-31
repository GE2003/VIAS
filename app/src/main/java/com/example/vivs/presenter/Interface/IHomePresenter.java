package com.example.vivs.presenter.Interface;

import com.example.vivs.Base.IBasePresenter;
import com.example.vivs.view.Interface.INewsCallback;

public interface IHomePresenter extends IBasePresenter<INewsCallback> {

 void  getCategories();
}
