package com.example.vivs.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.example.vivs.R;
import com.example.vivs.utils.SharePreference;

public class AnimationActivity extends AppCompatActivity {

    private LottieAnimationView view;
    private boolean isLogin;
    private String TAG="AnimationActivity";
    private ImageView logo;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// 避免从桌面启动程序后，会重新实例化入口类的activity
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            //结束你的activity
            finish();
            return;
        }


        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_lottie);
        view = this.findViewById(R.id.lottie_animation);
        logo = this.findViewById(R.id.logo);
        logo.bringToFront();
        setAnimation();

    }

    private void CheckisLogin(Intent intent1,ActivityOptionsCompat compat,Intent intent2) {
        SharePreference sp = new SharePreference(AnimationActivity.this);
        isLogin = sp.getState();
        Log.d(TAG,"STATE-----"+isLogin);
        if (isLogin){
            startActivity(intent2,compat.toBundle());
           finish();
        }else {
            startActivity( intent1,compat.toBundle());
            sp.setState(true);
          finish();
        }
    }

    private void setAnimation() {
    view.addAnimatorListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {

            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(AnimationActivity.this);
            Intent entry = new Intent(AnimationActivity.this,EntryActivity.class);
            Intent home = new Intent(AnimationActivity.this,MainActivity.class);
            entry.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
              CheckisLogin(entry,compat,home);

        }
    });
    }
}
