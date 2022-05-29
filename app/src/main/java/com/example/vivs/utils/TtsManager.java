package com.example.vivs.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import com.example.vivs.ui.activity.ReadActivity;
import com.varunest.sparkbutton.SparkButton;

import java.util.Locale;

public class TtsManager implements TextToSpeech.OnInitListener {
    private static final String TAG = "TtsManager";
    public static TextToSpeech textToSpeech;
public ReadActivity activity;
public TtsManager (TextToSpeech textToSpeechs, ReadActivity readActivity){
    textToSpeech=textToSpeechs;
        this.activity=readActivity;
}

    public  void speak(String title,String content){
        textToSpeech.speak(title, TextToSpeech.QUEUE_ADD, null,null);
        textToSpeech.speak(content, TextToSpeech.QUEUE_ADD, null,"UniqueID");
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                Log.d("开始播放","开始播放-----"+utteranceId);
            }

            @Override
            public void onDone(String utteranceId) {
                Log.d("开始播放","结束播放播放-----------------");
                if (utteranceId.equals("UniqueID")){

               activity.enent();
                }
            }

            @Override
            public void onError(String utteranceId) {

            }
        });
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.d("READACTIVITY","result-----不支持");
            }
        }
        if (textToSpeech != null && !textToSpeech.isSpeaking()) {
            // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
            textToSpeech.setPitch(1.5f);
            //设定语速 ，默认1.0正常语速
            textToSpeech.setSpeechRate(1.5f);
            //朗读，注意这里三个参数的added in API level 4   四个参数的added in API level 21
        }

    }
}

