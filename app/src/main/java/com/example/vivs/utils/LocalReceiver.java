package com.example.vivs.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LocalReceiver extends BroadcastReceiver {
    public static Activity activities;
    public static LocalReceiver getInstance(Activity activity){
        activities=activity;
        return new LocalReceiver();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int exit = intent.getIntExtra("exit",1);
        if (exit==1){
            Log.d("tag","dddddddddddddddddd");
            activities.finish();

        }
    }


}
