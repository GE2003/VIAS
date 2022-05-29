package com.example.vivs.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import com.example.vivs.Base.BaseFragment;
import com.example.vivs.R;
import com.example.vivs.ui.activity.EntryActivity;
import com.example.vivs.utils.BroadCastManager;
import com.example.vivs.utils.SharePreference;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.skydoves.elasticviews.ElasticButton;

import java.util.Objects;

public class SettingFragment extends BaseFragment {
public  Context context;

public SettingFragment settingFragment;
    private SharePreference sharePreference;

    public SettingFragment getInstance(){
    settingFragment=new SettingFragment();
    return  settingFragment;
}

    @Override
    protected void getFragmentContext(Context context) {
             this.context=context;
        sharePreference = new SharePreference(context);

    }
    @BindView(R.id.exit_app_btn)
    public ElasticButton ExitButton ;

    @Override
    protected int getResId() {
        return R.layout.fragment_setting;
    }
    @Override
    protected void onClicklistener() {
        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG","SETTINGFRAGMENT-----"+"CLICK");
                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(context);
                dialogBuilder
                        .withTitle("退出提示")
                        .withMessage("您确定要退出登录吗?")
                        .withButton1Text("确定")
                        .withButton2Text("取消")
                        .withButtonDrawable(R.color.white)
                        .withDialogColor("#00BFFF")
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                              Intent intent =new Intent("MY_BROADCAST");
                              intent.putExtra("exit",1);
                                sharePreference.setState(false);

                                BroadCastManager.getInstance().sendBroadCast(Objects.requireNonNull(getActivity()),intent);
                              startActivity(new Intent(getActivity(),EntryActivity.class));
                                Toast.makeText(getActivity(), "退出成功",Toast.LENGTH_SHORT).show();


                                Objects.requireNonNull(getActivity()).onBackPressed();


                            }
                        })
                        .setButton2Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogBuilder.cancel();
                            }
                        })
                        .show();
            }
        });

    }

    @Override
    protected void LoadData() {

    }

}
