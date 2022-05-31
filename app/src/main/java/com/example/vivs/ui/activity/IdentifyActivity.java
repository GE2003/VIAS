package com.example.vivs.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.transition.Transition;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.transition.TransitionInflater;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.example.vivs.Base.BaseActivity;
import com.example.vivs.R;
import com.example.vivs.utils.*;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IdentifyActivity extends BaseActivity {
    private static final int CAMERA_OK = 1;
    private static final String TAG = "IdentifyActivity";
    @BindView(R.id.take_photo_bt)
    public CardView take_photo_bt;
    @BindView(R.id.search_history_bt)
    public CardView search_history_bt;

    public static final int TAKE_PHOTO = 1;

    public Unbinder mBind;
    private ImageView picture;
    private Uri imageUri;
    private final String filePath = Environment.getExternalStorageDirectory() + File.separator + "output_image.jpg";
    private String picturebase64;
    private LocalReceiver instance;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ReceiveMsg();
        mBind = ButterKnife.bind(IdentifyActivity.this);
        picture = this.findViewById(R.id.img_show);
        setOnClickListener();
    }

    private void ReceiveMsg() {
        IntentFilter intentFilter = new IntentFilter("MY_BROADCAST");
        instance = LocalReceiver.getInstance(IdentifyActivity.this);
        BroadCastManager.getInstance().registerReceiver(this, instance, intentFilter);
    }

    private void requestpermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
        } else {
            //调用
            requestCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults != null && grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                case 1: {

                }
                break;
            }
        }
    }

    private void requestCamera() {
        File outputImage = new File(filePath);
                /*
                创建一个File文件对象，用于存放摄像头拍下的图片，我们把这个图片命名为output_image.jpg
                并把它存放在应用关联缓存目录下，调用getExternalCacheDir()可以得到这个目录，为什么要
                用关联缓存目录呢？由于android6.0开始，读写sd卡列为了危险权限，使用的时候必须要有权限，
                应用关联目录则可以跳过这一步
                 */
        try//判断图片是否存在，存在则删除在创建，不存在则直接创建
        {
            if (!outputImage.getParentFile().exists()) {
                outputImage.getParentFile().mkdirs();
            }
            if (outputImage.exists()) {
                outputImage.delete();
            }

            outputImage.createNewFile();

            if (Build.VERSION.SDK_INT >= 24) {
                imageUri = FileProvider.getUriForFile(this,
                        "com.example.vivs.fileprovider", outputImage);
            } else {
                imageUri = Uri.fromFile(outputImage);
            }
            //使用隐示的Intent，系统会找到与它对应的活动，即调用摄像头，并把它存储
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);
            //调用会返回结果的开启方式，返回成功的话，则把它显示出来
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void setOnClickListener() {
        take_photo_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("6666666666", "点击");
                //进行拍照.调用相机
                requestpermissions();
                requestCamera();
                //将照片上传
                setimg();
                //     UploadPhoto(picturebase64);
            }
        });
        search_history_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查看拍照搜索的历史记录
            }
        });
    }

    private void setimg() {
        File outputImage = new File(filePath);

        if (!outputImage.exists()) {
            Log.d(TAG, "这里");
        }
        // picture.setImageBitmap(BitmapFactory.decodeFile(filePath));
    }

    private void UploadPhoto(String Base64Pic) {
        // Log.d(TAG,"base64----"+Base64Pic);
        Retrofit retrofit = RetrofitManager.getInstance(Constants.BASE_MOBILE_URL).getRetrofit();
        API api = retrofit.create(API.class);
        Call<Object> task = api.getPicResult("application/json", Base64Pic);
        task.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d(TAG, "response----------" + response.body());
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d(TAG, "ERROR----------" + t);
            }
        });

        //调用移动云api

        //处理结果
        //Todo
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestcode--------" + requestCode);
        switch (requestCode) {
            case TAKE_PHOTO:
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    byte[] bytes = bitmap2Byte(bitmap);
                    picturebase64 = byte2Base64(bytes);
                    UploadPhoto(picturebase64);

                    //将图片解析成Bitmap对象，并把它显现出来
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }

    }

    public static byte[] bitmap2Byte(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //把bitmap100%高质量压缩 到 output对象里
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream);
        return outputStream.toByteArray();
    }

    /**
     * 将图片转成byte数组
     *
     * @param imageByte 图片
     * @return Base64 String
     */
    public static String byte2Base64(byte[] imageByte) {
        if (null == imageByte) return null;
        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initview() {

    }

    @Override
    protected int getLayoutResId() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right);
        getWindow().setEnterTransition(transition);
        return R.layout.activity_identify;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BroadCastManager.getInstance().unregisterReceiver(this, instance);
    }
}
