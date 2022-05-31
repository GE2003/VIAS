package com.example.vivs.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.airbnb.lottie.LottieAnimationView;
import com.example.vivs.Base.BaseFragment;
import com.example.vivs.R;
import com.example.vivs.ui.fragment.*;
import com.example.vivs.utils.AudioRecorder;
import com.example.vivs.utils.BroadCastManager;
import com.example.vivs.utils.Constants;

import com.example.vivs.utils.LocalReceiver;
import com.google.gson.Gson;
import com.lx.cloud.ai.SpeechUtility;
import com.lx.cloud.ai.iat.CallBack;
import com.lx.cloud.ai.iat.IatClient;
import com.lx.cloud.ai.iat.IatResult;
import com.lx.cloud.ai.iat.SessionBean;
import com.skydoves.elasticviews.ElasticCardView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yarolegovich on 25.03.2017.
 */
public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.photo_bt)
    public CardView photo_bt;
    @BindView(R.id.personal_bt)
    public CardView personal_bt;
    @BindView(R.id.speak_bt)
    public ElasticCardView speak_bt;
    @BindView(R.id.stop_bt)
    public ElasticCardView stop_bt;
    @BindView(R.id.listening)
    public LottieAnimationView animationView;
    public FavoriteFragment favoriteFragment;
    public Unbinder mBind;
    private PersonalFragment personalFragment;
    private MainFragment mainFragment;
 public AudioRecorder audioRecorder;
    private static final int GET_RECODE_AUDIO = 1;
    private static String[] PERMISSION_ALL = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private SettingFragment settingFragment;
    private ReadHistoryFragment readHistoryFragment;
    private NewsContentFragment newsContentFragment;
    private String res;
    private LocalReceiver instance;
    private NewsFragment newsFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpeechUtility.getInstance().init(Constants.accessKey,Constants.Signature);
        audioRecorder = AudioRecorder.getInstance();
      mBind = ButterKnife.bind(MainActivity.this);
        initFragment();
        setonClickListener();
      verifyPermissions(MainActivity.this);
      ReceiveMsg();
    }
    private void ReceiveMsg() {
        IntentFilter intentFilter = new IntentFilter("MY_BROADCAST");
        instance = LocalReceiver.getInstance(MainActivity.this);
        BroadCastManager.getInstance().registerReceiver(this, instance,intentFilter);
    }
    private void handleResultCallBack(String res) {
       if (Constants.IdentifyKeyWords.equals(res)){
           Log.d(TAG,"mainactivity------"+"拍照界面跳转");
           Intent intent = new Intent(MainActivity.this,IdentifyActivity.class);
           startActivity(intent);
       }

        else if (Constants.NewsKeyWords.contains(res)) {
            newsContentFragment = NewsContentFragment.getNewsFragment(res);
            switchFragment(newsFragment);
        }
        else if (Constants.ReadHistoryKeyWords.contains(res)){
            switchFragment(readHistoryFragment);
        }
        else if (Constants.SettingKeyWords.contains(res)){
            switchFragment(settingFragment);
        }else if (Constants.FavoriteKeyWords.contains(res)){
            switchFragment(favoriteFragment);
        }else if (Constants.EditMsgKeyWords.contains(res)){
            Intent intent = new Intent(MainActivity.this,PersonalActivity.class);

        }

        }



    private void SpeakToText() {
       File file = new File(Constants.RealPhoneRecordFilePath);
        SessionBean sessionBean = new SessionBean();
        sessionBean.setAue("raw");
        sessionBean.setSid("1212");
        sessionBean.setLanguage("cn");
        sessionBean.setBos(15000);
        sessionBean.setEos(15000);
        IatClient.getInstance().startListening(file, sessionBean, new CallBack.IatListen() {
            @Override
            public void onIatResponse(IatResult iatResult) {
                Gson gson = new Gson();
                String json1 = gson.toJson(iatResult);
                Log.d("test----", "result-----------" + json1);
                int count = 0;
                String regex = "[\u4e00-\u9fa5]";
                res = "";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(json1);
                while (count < json1.length()) {
                    if (m.find()) {
                        res = res + m.group();
                    }
                    count++;
                }
                Log.d("test----", "result-----------" + res);

                handleResultCallBack(res);
            }

            @Override
            public void onIatError(String s, String s1, String s2) {
                Log.d("test----", "error-----------" + s);
                Log.d("test----", "error-----------" + s1);
                Log.d("test----", "error-----------" + s2);
            }
        });
    }

    public static void verifyPermissions(Activity activity) {
        boolean permission = (ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED);
        if (permission) {
            ActivityCompat.requestPermissions(activity, PERMISSION_ALL,
                    GET_RECODE_AUDIO);
        }
    }
    private void setonClickListener() {
        photo_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG","CLICK-----");

             //   ActivityOptions compat = ActivityOptions.makeScaleUpAnimation(photo_bt, photo_bt.getWidth(), photo_bt.getHeight(),0,0);

                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(new Intent(MainActivity.this, IdentifyActivity.class), compat.toBundle());
                    finish();

                //startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });

        personal_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(new Intent(MainActivity.this, PersonalActivity.class), compat.toBundle());

            }
        });
        speak_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(MainActivity.this,"开始录音",Toast.LENGTH_SHORT).show();
                    animationView.bringToFront();
                    animationView.setVisibility(View.VISIBLE);
                    animationView.playAnimation();

                   if (audioRecorder.getStatus() == AudioRecorder.Status.STATUS_NO_READY) {
                       //初始化录音
                       String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                       audioRecorder.createDefaultAudio(fileName);
                       audioRecorder.startRecord(null);
                       speak_bt.setVisibility(View.GONE);
                       stop_bt.setVisibility(View.VISIBLE);
               }
            }
        });
        stop_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               animationView.cancelAnimation();
               animationView.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,"录音完成",Toast.LENGTH_SHORT).show();
                audioRecorder.stopRecord();
                stop_bt.setVisibility(View.GONE);
                speak_bt.setVisibility(View.VISIBLE);
                try {
                  //  Convert.convertAudioFiles(Constants.MoNiPhoneRecordFilePath,"/data/data/com.example.vivs/DATA/speak.wav");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                   SpeakToText();
            }
        });
    }


    private void initFragment() {
        newsFragment = new NewsFragment();
        personalFragment = new PersonalFragment();
        favoriteFragment = new FavoriteFragment();
        settingFragment = new SettingFragment();
        readHistoryFragment = new ReadHistoryFragment();
        mainFragment = new MainFragment();
        switchFragment(mainFragment);
    }


    private void switchFragment(BaseFragment target) {
             FragmentManager fm = getSupportFragmentManager();
             FragmentTransaction ft = fm.beginTransaction();
             ft.replace(R.id.main_page_container,target);
             ft.commit();
    }
    @Override
    protected void onDestroy() {
        audioRecorder.release();
        BroadCastManager.getInstance().unregisterReceiver(this,instance);
        super.onDestroy();
    }
}






