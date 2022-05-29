package com.example.vivs.utils;

import com.example.vivs.model.domin.FavoriteDatas;
import com.example.vivs.model.domin.FourHotNews;
import com.example.vivs.model.domin.NewsData;
import com.example.vivs.model.domin.NewsTotal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    public static   String accessKey = "b79cdb40f36f4ab0a2a7852c6af0f958";
    public static String Signature =  "b623eb517f29499488b5f5b65bb42beb";
    @GET("/favorite/getAllFavorite")
    Call<FavoriteDatas> getFavoriteData();
     @POST("/api/ocr/v1/webimage?AccessKey="+accessKey+"&SignatureMethod=HmacSHA1"+"&SignatureNonce=f20198f6f88c427a47b5df559"+"&SignatureVersion=V2.0"+"&Timestamp=2022-05-05T10%3A55%3A53Z&"+"Signature="+Signature)
    Call<Object> getPicResult(@Header("Content-Type")String header, @Query("image")String imageBase64);
     @GET("/new/getCount")
    Call<NewsTotal> getNewsTotal();
      @GET("/new/getFourHotNew")
    Call<FourHotNews> getFourHotNews();
      @GET("/")
}
