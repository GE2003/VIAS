package com.example.vivs.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.airbnb.lottie.LottieAnimationView;
import com.example.vivs.Base.BaseActivity;
import com.example.vivs.R;
import com.example.vivs.utils.TtsManager;
import com.varunest.sparkbutton.SparkButton;
import java.util.Locale;

public class ReadActivity extends BaseActivity implements TextToSpeech.OnInitListener {
    private static final ReadActivity ourInstance =new ReadActivity();
    private TtsManager ttsManager;
    private String Stitle;
    private String Scontent;

    public  static  ReadActivity getInstance(){return  ourInstance;}
     @SuppressLint("NonConstantResourceId")
     @BindView(R.id.news_content)
     public TextView content;
     @BindView(R.id.thumb_bt)
     public SparkButton thumb_sp;
     @BindView(R.id.news_title)
     public TextView title;
     @BindView(R.id.speak_btn)
     public SparkButton speak;
    private TextToSpeech textToSpeech;
@BindView(R.id.speak_view)
public LottieAnimationView animationView;

    @Override
    protected void initPresenter() {

    }



    @Override
    protected void initevent() {
            setOnClickListener();
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {


            }

            @Override
            public void onDone(String utteranceId) {




            }


            @Override
            public void onError(String utteranceId) {

            }
        });

    }

    @Override
    protected void initview() {
        textToSpeech=new TextToSpeech(ReadActivity.this,ReadActivity.this);
       animationView.setVisibility(View.GONE);
        Intent intent = this.getIntent();
        Stitle = intent.getStringExtra("title");
        Scontent = intent.getStringExtra("content");
        if (Stitle!=null&&Scontent!=null){
        title.setText(Stitle);
        content.setText(Scontent);
        }
    }
    public void setOnClickListener() {
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak.setVisibility(View.GONE);
                animationView.setVisibility(View.VISIBLE);
                animationView.playAnimation();
                ttsManager = new TtsManager(textToSpeech,ReadActivity.this);

                ttsManager.speak(title.getText().toString(),content.getText().toString());

            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_read;
    }

public void enent(){
    Log.d("开始播放","监听事件-----------------");
    ReadActivity.this.runOnUiThread(new Runnable() {
        @Override
        public void run() {

    animationView.cancelAnimation();
    animationView.setVisibility(View.GONE);
    speak.setVisibility(View.VISIBLE);
        }
    });
}

    @Override
    public void onInit(int status) {


    }


    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.stop();
        textToSpeech.shutdown();
    }


}

