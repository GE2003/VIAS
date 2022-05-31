package com.example.vivs.utils;

import com.example.vivs.model.domin.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.lang.reflect.Array;
import java.util.Arrays;

public interface API {
    public static String accessKey = "b79cdb40f36f4ab0a2a7852c6af0f958";
    public static String Signature = "b623eb517f29499488b5f5b65bb42beb";

    //new-controller

    @POST("/api/ocr/v1/webimage?AccessKey=" + accessKey + "&SignatureMethod=HmacSHA1" + "&SignatureNonce=f20198f6f88c427a47b5df559" + "&SignatureVersion=V2.0" + "&Timestamp=2022-05-05T10%3A55%3A53Z&" + "Signature=" + Signature)
    Call<Object> getPicResult(@Header("Content-Type") String header,
                              @Query("image") String imageBase64);

    @GET("new/getCount")
    Call<NewsTotal> getNewsTotal();

    @GET("new/getFourHotNew")
    Call<FourHotNews> getFourHotNews();

    @GET
    Call<NewsData> getNewsByCategoryId(@Url String GetCategoryNewsURL);

    @GET
    Call<HotNewsData> getHotNewsData(@Url String GetHotNewsData);

    @GET
    Call<SingleNews> getNewsById(@Url String GetNewsByIdURL);

    @GET
    Call<AllNewsData> getAllNewsData(@Url String GetAllNewsData);

    @GET
    Call<FreeBackData> getFreeBackData(@Url String GetAllFreeBackURL);

    @GET
    Call<FavoriteDatas> getFavoriteData(@Url String getAllFavoriteData);

    @GET
    Call<AllLikesNewsData> getAllLikesNews(@Url String getAllLikesNewsURL);

    //favorite-controller
    @POST("favorite/addOrDeleteFavorite")
    Call<FavoriteMsg> AddOrDeleteFavorite(@Query("categoryId") int categoryId,
                                          @Query("content") String content,
                                          @Query("id") int id,
                                          @Query("likes") int likes,
                                          @Query("publishTime") String time,
                                          @Query("title") String title);

    //free-back-controller
    @POST("freeback/addFreeBack")
    Call<FreeBackMsg> PostFreeBack(@Query("content") String content,
                                                    @Query("id") int id,
                                                    @Query("userId") int userId);

    //like-controller
    @POST("like/addOrDeleteLike")
    Call<AODLmsg> AddOrDeleteLike(@Query("categoryId") int categoryId,
                                  @Query("content") String content,
                                  @Query("id") int id,
                                  @Query("likes") int likes,
                                  @Query("title") String title);


    @DELETE("newlogs/deleteNewMsg")
    Call<DeleteNewMsg> DeleteNewsLog(@Query("deleteNewUsingDELEEIds") Array id);

    @POST("photologs/addPhotoMsg")
    Call<AddPhotoMsg> AddPhotoLog(@Query("content") String content,
                                                   @Query("id") int id,
                                                   @Query("userId") int userId);

    @DELETE("photologs/deletePhotoMsg")
    Call<DeleteNewMsg> DeletePhotoLogs(@Query("deleteNewUsingDELETEIds") Array id);

    @GET
    Call<PhotoLogs> getPhotoLogs(@Url String PhotoLogsURL);
    @POST("user/login")
    Call<LoginData> LoginIn(@Query("password") String password,
                            @Query("phoneNumber")String number);
}
