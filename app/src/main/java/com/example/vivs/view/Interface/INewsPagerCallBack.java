package com.example.vivs.view.Interface;

import com.example.vivs.model.domin.news;

import java.util.List;

public interface INewsPagerCallBack {
    //这个接口的设计是为了拿到数据后的UI更新
    void  onTitleLoaded();//传一个标题的集合进去
    void  onNewsContentLoaded(List<news> records,int NewsModuleID);//获取新闻的内容
    int getCategoriessId();
    void  onLoading(int NewsModuleId);
    void  onError(int NewsModuleId);
    void  onEmpty(int NewsModuleId);
    void onLoaderMoreError(int NewsModuleId);
    void onLoaderMoreEmpty(int NewsModuleId);
    void onLoaderMoreSuccess();//添加集合
}
