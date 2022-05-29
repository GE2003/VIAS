package com.example.vivs.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.vivs.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.skydoves.elasticviews.ElasticButton;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {


    private MaterialEditText time_picker;
    private MaterialEditText gender_picker;
    private ElasticButton sign_up;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_bottom);
        getWindow().setEnterTransition(transition);
        setContentView(R.layout.activity_register);
         initview();
        setSelectTime();
        setSelectGender();
        setOnclickListener();
    }

    private void setOnclickListener() {

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up.setVisibility(View.GONE);
                animationView.setVisibility(View.VISIBLE);
                animationView.playAnimation();
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }
        });
        animationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(RegisterActivity.this);
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity( intent,compat.toBundle());
                finish();
            }
        });
    }

    private void setSelectGender() {
        ArrayList<String> optionItems = new ArrayList<>();
        optionItems.add("男");
        optionItems.add("女");

        OptionsPickerView pvOptions = new OptionsPickerBuilder(RegisterActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String s = optionItems.get(options1);
                gender_picker.setText(s);
            }
        }).build();
        pvOptions.setPicker(optionItems);



        gender_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
pvOptions.show();
            }
        });
    }

    private void initview() {
        time_picker = this.findViewById(R.id.time_picker_btn);
        gender_picker = this.findViewById(R.id.gender_picker_btn);
        sign_up = this.findViewById(R.id.sign_up_rg);
       animationView = this.findViewById(R.id.rg_success_aim);
        animationView.setVisibility(animationView.GONE);
    }

    private void setSelectTime() {
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);
        //正确设置方式 原因：注意事项有说明
        startDate.set(2013,0,1);
        endDate.set(2020,11,31);

        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(RegisterActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(RegisterActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
                time_picker.setText(getTime(date));
            }
        })
                .build();
    time_picker.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pvTime.show();

        }
    });
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

}
