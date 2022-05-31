package com.example.vivs.ui.fragment;


import android.content.Context;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.example.vivs.Base.BaseFragment;
import com.example.vivs.R;
import com.example.vivs.model.domin.Category;
import com.example.vivs.model.domin.news;
import com.example.vivs.presenter.Interface.INewsPagerPresenter;

import com.example.vivs.presenter.impl.NewsPagerPresenterImpl;

import com.example.vivs.ui.activity.ReadActivity;
import com.example.vivs.ui.adapter.NewsPagerContentAdapter;
import com.example.vivs.view.Interface.INewsPagerCallBack;
import org.jetbrains.annotations.NotNull;


import java.util.List;


public class NewsContentFragment extends BaseFragment implements INewsPagerCallBack, NewsPagerContentAdapter.OnListItemClickListener {
  public Context Newscontext;
    private static final String TAG = "NewsContentFragment";
    private INewsPagerPresenter newsPagerPresenter;
  private NewsPagerContentAdapter newsPagerContentAdapter;
    public  static  String Res;
    private String title;
    private int id;
@BindView(R.id.news_content_list)
public RecyclerView NewsContentList;
    public static NewsContentFragment newInstance(Category category){
    NewsContentFragment newsContentFragment = new NewsContentFragment();
    Bundle bundle = new Bundle();
    bundle.putInt("ID",category.getId());
    bundle.putString("title",category.getTitle());
    newsContentFragment.setArguments(bundle);
    return newsContentFragment;
}


public static NewsContentFragment getNewsFragment(String res){
        Res=res;

        Log.d(TAG,"res-----------"+res);

    return new NewsContentFragment();
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
        newsPagerPresenter =  NewsPagerPresenterImpl.getInstance();
        newsPagerPresenter.registerViewCallback(this);
    }




    @Override
    protected void getFragmentContext(Context context) {
       this.Newscontext =context;

    }

    @Override
    protected void initview(View view) {
            setUpState(State.SUCCESS);
          NewsContentList.setLayoutManager(new LinearLayoutManager(getContext()));
          NewsContentList.addItemDecoration(new RecyclerView.ItemDecoration() {
              @Override
              public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
                  outRect.top=55;
                  outRect.bottom=55;
              }
          });
          newsPagerContentAdapter=new NewsPagerContentAdapter();

          NewsContentList.setAdapter(newsPagerContentAdapter);



    }

    @Override
    protected void onClicklistener() {
        Log.d(TAG,"点击事件---------");
        newsPagerContentAdapter.setOnListItemClickListener(this);
    }

    @Override
    protected int getResId() {
        return R.layout.fragment_homepager;
    }

    @Override
    protected void LoadData() {
        Bundle arguments = getArguments();
        Log.d(TAG,"arguments---------"+arguments);
 if (arguments!=null){
     title = arguments.getString("title");
     id = arguments.getInt("ID");
     Log.d(TAG,"get Title-----------"+title);
     Log.d(TAG,"get id-----------"+id);
 }
        if (newsPagerPresenter!=null){
            //todo
        newsPagerPresenter.getTitleByNewsModuleId(id);
    }
    }

    @Override
    public void onTitleLoaded() {

    }

    @Override
    public void onNewsContentLoaded(List<news> records, int NewsModuleID) {
           setUpState(State.SUCCESS);
           newsPagerContentAdapter.setDATA(records);
    }





    @Override
    public int getCategoriessId() {
        return id;
    }

    @Override
    public void onLoading(int NewsModuleId) {

     setUpState(State.LOADING);
    }

    @Override
    public void onError(int NewsModuleId) {

setUpState(State.ERROR);
    }

    @Override
    public void onEmpty(int NewsModuleId) {

 setUpState(State.EMPTY);
    }

    @Override
    public void onLoaderMoreError(int NewsModuleId) {

    }

    @Override
    public void onLoaderMoreEmpty(int NewsModuleId) {

    }

    @Override
    public void onLoaderMoreSuccess() {

    }

    @Override
    public void onItemClick(news item) {
        //列表内容被点击
        Log.d(TAG,"On item click----------"+item.getTitle());
        Intent intent =new Intent(getActivity(), ReadActivity.class);
        intent.putExtra("title",item.getTitle());
        intent.putExtra("content",item.getContent());
        startActivity(intent);
    }
}
