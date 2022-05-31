package com.example.vivs.ui.fragment;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import com.example.vivs.Base.BaseFragment;

import com.example.vivs.R;
import com.example.vivs.model.domin.Category;
import com.example.vivs.presenter.impl.NewsPresenterImpl;
import com.example.vivs.ui.adapter.NewsPagerAdapter;
import com.example.vivs.utils.Constants;
import com.example.vivs.utils.ScaleTransitionPagerTitleView;
import com.example.vivs.view.Interface.INewsCallback;
import net.lucode.hackware.magicindicator.FragmentContainerHelper;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

public class NewsFragment extends BaseFragment implements INewsCallback {
    private static final String TAG = "NewsFragment";
    private NewsPresenterImpl newsPresenter;
    private NewsPagerAdapter newsPagerAdapter ;
    public Context Newscontext;
    private MagicIndicator Indicator;
    private ViewPager viewpager;
    public FragmentContainerHelper fragmentContainerHelper ;
    @Override
    protected int getResId() {
        return R.layout.fragment_home;
    }




    @Override
    protected void getFragmentContext(Context context) {
        this.Newscontext =context;
    }

    @Override
    protected void initPresenter() {
        newsPresenter = new NewsPresenterImpl();
        newsPresenter.registerViewCallback(this);
    }

    @Override
    protected void initview(View view) {
        viewpager=   view.getRootView().findViewById(R.id.view_pager_fr_test);
        Indicator= view.findViewById(R.id.news_indicator_test);
        newsPagerAdapter =new NewsPagerAdapter(getChildFragmentManager());
        fragmentContainerHelper  = new FragmentContainerHelper(Indicator);
         viewpager.setAdapter(newsPagerAdapter);
        initMagicIndicator2();
    }
    private void initMagicIndicator2() {


        Indicator.setBackgroundColor(Color.WHITE);

        CommonNavigator commonNavigator = new CommonNavigator(Newscontext);
        commonNavigator.setScrollPivotX(0.8f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {

                return Constants.Category.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(Constants.Category[index]);
                simplePagerTitleView.setTextSize(30);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.teal_200));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewpager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(3.9f));
                indicator.setYOffset(UIUtil.dip2px(context, 49));
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setColors(getResources().getColor(R.color.teal_200));
                return indicator;
            }
        });
        Indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(Indicator, viewpager);
    }

    @Override
    protected void onClicklistener() {

    }

    @Override
    protected void LoadData() {
        if (newsPresenter!=null){
            newsPresenter.getCategories();
        }
    }

    @Override
    public void onCategoriesLoaded(List<Category> category) {
        setUpState(State.SUCCESS);
        Log.d(TAG,"Getcategories----"+category);
        Log.d(TAG,"不空---------"+newsPagerAdapter);
        if (newsPagerAdapter!=null){
            newsPagerAdapter.setCategories(category);
        }
    }

    @Override
    public void onNetWorkError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }
}
