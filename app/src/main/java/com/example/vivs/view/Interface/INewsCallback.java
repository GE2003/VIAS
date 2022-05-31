package com.example.vivs.view.Interface;


import com.example.vivs.model.domin.Category;

import java.util.List;

public interface INewsCallback {
    void onCategoriesLoaded(List<Category> category);
    void onNetWorkError();
    void onLoading();
    void onEmpty();
}
