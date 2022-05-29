package com.example.vivs.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.lottie.LottieAnimationView;
import com.example.vivs.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.skydoves.elasticviews.ElasticButton;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = " LoginActivity";
    @BindView(R.id.number_input) 
    public MaterialEditText number_input;
    @BindView(R.id.password_input)
    public  MaterialEditText password_input;
    @BindView(R.id.sign_in_btn)
    public ElasticButton sign_in_btn;
    private String number;
    private String password;
    private LottieAnimationView animationView;
    private String helperText;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_bottom);
        getWindow().setEnterTransition(transition);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setOnclickListener();
    }

    private void getData() {
        number_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
        number =s.toString();

            }
        });

   password_input.addTextChangedListener(new TextWatcher() {
       @Override
       public void beforeTextChanged(CharSequence s, int start, int count, int after) {

       }

       @Override
       public void onTextChanged(CharSequence s, int start, int before, int count) {

       }

       @Override
       public void afterTextChanged(Editable s) {
          password=s.toString();
       }
   });
        animationView = this.findViewById(R.id.correct_aim);
        animationView.setVisibility(View.GONE);
    }

    private void setOnclickListener() {
        getData();
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (number.equals("18718069509")&&password.equals("2003")){
                    sign_in_btn.setVisibility(View.GONE);
                    animationView.setVisibility(View.VISIBLE);
                      animationView.playAnimation();
                  Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(LoginActivity.this,"密码或账号错误",Toast.LENGTH_SHORT).show();
              }

            }
        });
        animationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(LoginActivity.this);
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( intent,compat.toBundle());
                finish();
            }
        });
    }
}
