package com.example.vivs.ui.adapter;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.vivs.ui.fragment.NewsPageFragment;

import java.util.ArrayList;
import java.util.List;

public class NewsPagerAdapter extends FragmentPagerAdapter {

     List<Fragment> list = new ArrayList<>();
    private String[] titles = {"推荐", "热门", "娱乐","社会","科技","房产","财经","时尚","游戏","体育","时政","教育","娱乐"};
    public NewsPagerAdapter(@NonNull FragmentManager fm) {

        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
return titles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        Log.d("taasdasd","list size--------"+list.size());
        return list.size();
    }


    public void setData() {
        for (int i = 0; i < titles.length; i++) {

      list.add(new NewsPageFragment());
        }

    }
}
