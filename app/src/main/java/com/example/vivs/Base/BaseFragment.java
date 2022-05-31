  package com.example.vivs.Base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.vivs.R;
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    private  State currentState=State.SUCCESS;

    private View mSuccessView;
    private View mLoadingview;
    private View mErrorView;
    private View mEmptyView;
    private FrameLayout mBaseContainer;
    private Unbinder unbinder;

    public enum State{
        NONE,LOADING,SUCCESS,ERROR,EMPTY
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.base_layout, container,false);
        mBaseContainer=rootview.findViewById(R.id.base_layout_container);
      getFragmentContext(BaseFragment.this.getActivity());
        LoadStatesView(inflater,container);
         unbinder = ButterKnife.bind(this, rootview);
         initview(rootview);
        initPresenter();
        LoadData();
        onClicklistener();
        return rootview;
    }



    protected abstract void getFragmentContext(Context context);

    protected  void initview(View view){

    }

   protected    abstract void onClicklistener();

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (transit == FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
            if (enter) {
                // 表示普通的进入的动作，比如add、show、attach等
                return AnimationUtils.loadAnimation(getContext(), R.anim.fragment_second_3d_reversal_enter);
            } else {
                // 比如一个Fragmen已经被另一个replace，被replace的那个就是false
                return AnimationUtils.loadAnimation(getContext(), R.anim.fragment_second_3d_reversal_exit);
            }
        } else if (transit == FragmentTransaction.TRANSIT_FRAGMENT_CLOSE) {
            if (enter) {
                // 之前被replace的重新进入到界面或者Fragment回到栈顶
                return AnimationUtils.loadAnimation(getContext(), R.anim.fragment_second_3d_reversal_enter);
            } else {
                // 表示一个退出动作，比如出栈、remove、hide、detach等
                return AnimationUtils.loadAnimation(getContext(), R.anim.fragment_second_3d_reversal_exit);
            }
        }
        return null;
    }

    protected void  changeFragment(Fragment targetFragment){

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.animator.fragment_second_3d_reversal_enter,R.animator.fragment_second_3d_reversal_exit,R.animator.fragment_second_3d_enter,R.animator.fragment_second_3d_exit)
                .replace(R.id.main_page_container,targetFragment,null)
                .addToBackStack(null)
                .commit();
    }

    protected  void initPresenter(){

    }

    protected abstract int getResId();

    protected abstract void LoadData();

    private void   LoadStatesView(LayoutInflater inflater,ViewGroup container){
        //Loading的view
        mLoadingview = loadLoadingview(inflater, container);
        mBaseContainer.addView(mLoadingview);
       //加载成功的界面
        mSuccessView = loadSuccessView(inflater, container);
        mBaseContainer.addView(mSuccessView);

        //错误页面
        mErrorView = loadErrorView(inflater, container);
        mBaseContainer.addView(mErrorView);
        setUpState(State.SUCCESS);

    }

    public void setUpState(State currentState) {
        this.currentState=currentState;
        mSuccessView.setVisibility(currentState==State.SUCCESS?View.VISIBLE:View.GONE);
        mLoadingview.setVisibility(currentState==State.LOADING?View.VISIBLE:View.GONE);
        mErrorView.setVisibility(currentState==State.ERROR?View.VISIBLE:View.GONE);
        Log.d(TAG,"state----"+currentState);
    }

    protected  View loadSuccessView(LayoutInflater inflater, ViewGroup container){
        int resID =getResId();

        return  inflater.inflate(resID,container,false);
    }
    private View loadErrorView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_error,container,false);
    }

    private View loadLoadingview(LayoutInflater inflater, ViewGroup container) {

        return   inflater.inflate(R.layout.fragment_loading,container,false);
    }
    protected  void release(){}
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        release();
    }
}
