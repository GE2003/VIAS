package com.example.vivs.ui.adapter;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.vivs.model.domin.Category;
import com.example.vivs.ui.fragment.NewsContentFragment;
import com.example.vivs.ui.fragment.NewsFragment;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "NewsPagerAdapter";
    private List<Category> DataBeans = new ArrayList<>();
    public NewsPagerAdapter(@NonNull FragmentManager fm) {

        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
         Log.d(TAG,"Title------------"+DataBeans.get(position).getTitle());
        return DataBeans.get(position).getTitle();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d(TAG,"Fragment getItem----"+position);
        Category category = DataBeans.get(position);
        Log.d(TAG,"Fragment getItem  title----"+category.getTitle());
   NewsContentFragment newsContentFragment = NewsContentFragment.newInstance(category);
        return newsContentFragment;
    }

    @Override
    public int getCount() {

        return DataBeans.size();
    }


    public void setCategories(List<Category> category){
        DataBeans.clear();
        Log.d(TAG,"News setCategories---"+category.size());
        this.DataBeans.addAll(category);
        notifyDataSetChanged();

    }
}
