package com.example.vivs.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.example.vivs.Base.BaseFragment;
import com.example.vivs.R;
import com.example.vivs.presenter.impl.HomePresenterImpl;

import com.example.vivs.ui.adapter.NewsPagerAdapter;
import com.example.vivs.utils.Constants;
import com.example.vivs.view.Interface.IHomeCallback;
import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;


public class NewsFragment extends BaseFragment implements IHomeCallback {
  public Context Newscontext;
    private static final String TAG = "HomeFragment";
    private HomePresenterImpl homePresenter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.home_indicator)
    public MagicIndicator magicIndicator ;
 /*   @BindView(R.id.home_indicator)*/
/*    public  TabLayout tabLayout;*/
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.home_pager)
    public ViewPager homepager;
    public  static  String Res;
   public FragmentContainerHelper fragmentContainerHelper ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

public static NewsFragment getNewsFragment(String res){
        Res=res;

        Log.d(TAG,"res-----------"+res);

    return new NewsFragment();
}

public  void setSelectTabByres(String res){
       /* if (res==null||res.contains("推荐")){
    tabLayout.getTabAt(0).select();
        }else if(res.contains("热门")){
    tabLayout.getTabAt(1).select();
        }else if (res.contains("军事")){
    tabLayout.getTabAt(3).select();
        }else if (res.contains("科技")){
            tabLayout.getTabAt(4).select();
        }else if (res.contains("国际")){
            tabLayout.getTabAt(5).select();
        }else if (res.contains("财经")){
            tabLayout.getTabAt(6).select();
        }else if (res.contains("美食")){
            tabLayout.getTabAt(7).select();
        }else if (res.contains("情感")){
            tabLayout.getTabAt(8).select();
        }else if (res.contains("体育")){
            tabLayout.getTabAt(9).select();
        }else if (res.contains("时政")){
            tabLayout.getTabAt(10).select();
        }*/
}

    @Override
    protected void initPresenter() {

    }




    @Override
    protected void getFragmentContext(Context context) {
       this.Newscontext =context;

    }

    @Override
    protected void initview(View view) {
        homePresenter = new HomePresenterImpl();
        homePresenter.registerViewCallback(this);
        NewsPagerAdapter newsPagerAdapter = new NewsPagerAdapter(getChildFragmentManager());
        newsPagerAdapter.setData();
        homepager.setAdapter(newsPagerAdapter);
        fragmentContainerHelper  = new FragmentContainerHelper(magicIndicator);
        initMagicIndicator();
    }
public void initMagicIndicator(){

        magicIndicator.setBackgroundResource(R.drawable.round_indicator_bg);

        CommonNavigator commonNavigator = new CommonNavigator(Newscontext);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return Constants.Category.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(Constants.Category[index]);
                clipPagerTitleView.setTextColor(Color.BLACK);
                clipPagerTitleView.setClipColor(getResources().getColor(R.color.ghostwhite));
                clipPagerTitleView.setTextSize(100);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     //  fragmentContainerHelper.handlePageSelected(index);
                       homepager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float navigatorHeight = context.getResources().getDimension(R.dimen.common_navigator_height);
                float borderWidth = UIUtil.dip2px(context, 1);
                float lineHeight = navigatorHeight - 2 * borderWidth;
                indicator.setLineHeight(210);
                //lineHeight / 2
                indicator.setRoundRadius(73);
                indicator.setYOffset(borderWidth);
                indicator.setColors(getResources().getColor(R.color.deepskyblue));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
       fragmentContainerHelper.attachMagicIndicator(magicIndicator);

    /*
    magicIndicator.setBackgroundColor(Color.WHITE);
    CommonNavigator commonNavigator= new CommonNavigator(Newscontext);
    commonNavigator.setScrollPivotX(0.35f);
*//*
 commonNavigator.setSmoothScroll(true);
   commonNavigator.setFollowTouch(true);
 *//*

    commonNavigator.setAdapter(new CommonNavigatorAdapter() {
        @Override
        public int getCount() {
            return Constants.Category.length;
        }

        @Override
        public IPagerTitleView getTitleView(Context context, int index) {
*//*            ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(Newscontext);
            clipPagerTitleView.setText(Constants.Category[index]);

clipPagerTitleView.setTextColor(Color.parseColor("#e94200"));
clipPagerTitleView.setClipColor(Color.WHITE);
clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        homepager.setCurrentItem(index);
    }
});
return clipPagerTitleView;*//*
            SimplePagerTitleView simplePagerTitleView =new SimplePagerTitleView(Newscontext);
            simplePagerTitleView.setText(Constants.Category[index]);
            simplePagerTitleView.setNormalColor(Color.parseColor("#333333"));
            simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.teal_200));
            simplePagerTitleView.setTextSize(30);
            simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homepager.setCurrentItem(index);
                }
            });
            return  simplePagerTitleView;


       *//*     ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
            colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
            colorTransitionPagerTitleView.setTextSize(40);

            colorTransitionPagerTitleView.setSelectedColor(getResources().getColor(R.color.teal_200));
            colorTransitionPagerTitleView.setText(Constants.Category[index]);
            colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentContainerHelper.handlePageSelected(index);
                    homepager.setCurrentItem(index);
                }
            });
            return colorTransitionPagerTitleView;*//*
        }

        @Override
        public IPagerIndicator getIndicator(Context context) {
            WrapPagerIndicator wrapPagerIndicator = new WrapPagerIndicator(context);

            wrapPagerIndicator.setFillColor(Color.parseColor("#ebe4e3"));
            wrapPagerIndicator.setRoundRadius(100);

            LinePagerIndicator indicator = new LinePagerIndicator(context);

            indicator.setColors(getResources().getColor(R.color.teal_200));
            indicator.setRoundRadius(20);
            indicator.setLineHeight(20);
          indicator.setLineWidth(10);
            indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);

            return wrapPagerIndicator;
        }
    });
*/
//    magicIndicator.setNavigator(commonNavigator);

    ViewPagerHelper.bind(magicIndicator,homepager);
    //设置适配器等等

    /*   tabLayout.setupWithViewPager(homepager);*/
    setSelectTabByres(Res);
    Log.d(TAG,"设置tablayout---------");
}
    @Override
    protected void onClicklistener() {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void LoadData() {

    }

    @Override
    public void onNetWorkError() {
setUpState(State.ERROR);
    }

    @Override
    public void onLoading() {
    setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {
          setUpState(State.EMPTY);
    }



}
