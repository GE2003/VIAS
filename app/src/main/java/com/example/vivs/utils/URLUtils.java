package com.example.vivs.utils;

public class URLUtils {



    public static String GetCategoryNewsURL(String baseUrl, int categoryId, int pageNo, int pageSize) {
        //根据种类类型查询新闻信息
        String PageNo = String.valueOf(pageNo);
        String PageSize = String.valueOf(pageSize);
        String CategoryID = String.valueOf(categoryId);
        String CategoryNewsUrl = baseUrl + "new/getByCategory/" + PageNo + "/" + PageSize + "/" + CategoryID;
        return CategoryNewsUrl;
    }

    public static String GetCheckHotNewsURL(String baseUrl, int pageNo, int pageSize) {
        //查询热门新闻的信息
        String PageNo = String.valueOf(pageNo);
        String PageSize = String.valueOf(pageSize);
        String CheckHotNewsURL = baseUrl + "new/getHotNew/" + PageNo + "/" + PageSize;
        return CheckHotNewsURL;
    }

    public static String GetNewsByIdURL(String baseUrl, int newsID) {
        //根据ID查询新闻信息
        String NewsID = String.valueOf(newsID);
        String GetNewsUrl = baseUrl + "new/getNew/" + NewsID;
        return GetNewsUrl;
    }

    public static String GetAllNewsURL(String baseUrl, int pageNo, int pageSize) {
        //分页查询新闻信息
        //TODO
        String PageNo = String.valueOf(pageNo);
        String PageSize = String.valueOf(pageSize);
        String getAllNewsURL = baseUrl + "new/getAllNew/" + PageNo + "/" + PageSize;
        return getAllNewsURL;
    }

    public static String GetAllFavoriteURL(String baseUrl, int pageNo, int pageSize) {
        //分页查询所有收藏的新闻
        String PageNo = String.valueOf(pageNo);
        String PageSize = String.valueOf(pageSize);
        String GetAllFavoriteUrl = baseUrl + "favorite/getAllFavorite/" + PageNo + "/" + PageSize;
        return GetAllFavoriteUrl;
    }

    public static String GetAllFreeBackURL(String baseUrl, int pageNo, int pageSize) {
        //分页查询所有反馈
        String PageNo = String.valueOf(pageNo);
        String PageSize = String.valueOf(pageSize);
        String GetAllFreeBackURL = baseUrl + "freeback/getMsg/" + PageNo + "/" + PageSize;
        return GetAllFreeBackURL;
    }

    public static String GetAllLikesNew(String baseUrl, int pageNo, int pageSize) {
        //分页查询所有点赞新闻
        String PageNo = String.valueOf(pageNo);
        String PageSize = String.valueOf(pageSize);
        String GetAllLikesNews = baseUrl + "like/getAllLikeNew/" + PageNo + "/" + PageSize;
        return GetAllLikesNews;
    }
public static String GetPhotoLogsURL(String baseUrl,int pageNo,int pageSize){
        //查询拍照识物的记录
    String PageNo = String.valueOf(pageNo);
    String PageSize = String.valueOf(pageSize);
    String PhotoLogsURL = baseUrl+"photologs/getPhotoMsg/"+PageNo+"/"+PageSize;
    return PhotoLogsURL;
}
}
