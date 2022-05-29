package com.example.vivs.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import androidx.core.app.ActivityOptionsCompat;
import com.airbnb.lottie.LottieAnimationView;
import com.example.vivs.R;
import com.skydoves.elasticviews.ElasticButton;

public class EntryActivity extends AppCompatActivity {
    private LottieAnimationView view;
    private ElasticButton sign_in;
    private ElasticButton sign_up;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_bottom);
        getWindow().setEnterTransition(transition);
        setContentView(R.layout.activity_entry);
         initview();
         setOnclickListener();
    }

    private void setOnclickListener() {
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(EntryActivity.this);
                startActivity( new Intent(EntryActivity.this,LoginActivity.class),compat.toBundle());
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(EntryActivity.this);
                startActivity( new Intent(EntryActivity.this,RegisterActivity.class),compat.toBundle());
            }
        });
    }

    private void initview() {
        view=this.findViewById(R.id.view);
        sign_in = this.findViewById(R.id.sign_in);
        sign_up = this.findViewById(R.id.sign_up);
    }
}
