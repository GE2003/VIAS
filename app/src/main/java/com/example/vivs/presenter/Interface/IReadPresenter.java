package com.example.vivs.presenter.Interface;

import com.example.vivs.Base.IBasePresenter;
import com.example.vivs.view.Interface.IReadCallback;

public interface IReadPresenter extends IBasePresenter<IReadCallback> {
//presenter的接口进行拿数据,把数据给到UI的回调接口等操作
    void getContentByTitle(String title);
    void setTitle(String title);
    void setLikeBtnClickListener();
    void setCollectBtnClickListener();

}
