package com.example.vivs.ui.fragment;

import android.content.Context;
import android.view.View;


import androidx.cardview.widget.CardView;
import butterknife.BindView;

import com.example.vivs.Base.BaseFragment;
import com.example.vivs.R;


public class MainFragment extends BaseFragment {
    @BindView(R.id.cw_more_fr)
    public CardView personal_bt;
    @BindView(R.id.cw_read_fr)
        public CardView read_bt;

    @Override
    protected int getResId() {

        return R.layout.fragment_main;
    }

    @Override
    protected void LoadData() {

    }




    @Override
    protected void getFragmentContext(Context context) {

    }

    @Override
    protected void initview(View view) {

        personal_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new PersonalFragment());


            }
        });
        read_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changeFragment(new NewsFragment());
            }
        });

    }

    @Override
    protected void onClicklistener() {

    }

}
