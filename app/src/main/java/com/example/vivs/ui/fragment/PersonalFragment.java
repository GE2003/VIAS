package com.example.vivs.ui.fragment;

import android.content.Context;
import android.view.View;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import com.example.vivs.Base.BaseFragment;
import com.example.vivs.R;

public class PersonalFragment extends BaseFragment  {
    @BindView(R.id.cw_setting_bt)
    public CardView setting_bt;
    @BindView(R.id.cw_readhistory_bt)
    public CardView readhistory_bt;
    @BindView(R.id.cw_favorite_bt)
    public CardView favorite_bt;
    @Override
    protected int getResId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void LoadData() {

    }


    @Override
    protected void getFragmentContext(Context context) {

    }

    @Override
    protected void initview(View view) {
        setting_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new SettingFragment());
            }
        });
        readhistory_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new ReadHistoryFragment());
            }
        });
        favorite_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new FavoriteFragment());
            }
        });

    }

    @Override
    protected void onClicklistener() {

    }


}
