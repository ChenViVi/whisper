package com.valorachen.whisper.http.service;

import com.valorachen.whisper.model.Article;
import com.valorachen.whisper.model.Type;
import com.valorachen.whisper.model.base.BaseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by p_yuweichen on 2017/12/21.
 */

public interface RequestService {

    @GET("request/type_get.php")
    Call<Type> type_get();

    @GET("request/type_update.php")
    Call<BaseModel> type_update(@Query("id") int id, @Query("name") String name);

    @GET("request/type_delete.php")
    Call<BaseModel> type_delete(@Query("id") int id);

    @GET("request/article_get.php")
    Call<Article> article_get(@Query("type_id") int type_id);

    @GET("request/article_add.php")
    Call<BaseModel> article_add(@Query("type") String type, @Query("content") String content);

    @GET("request/article_update.php")
    Call<BaseModel> article_update(@Query("id") int id, @Query("type") String type, @Query("content") String content);

    @GET("request/article_delete.php")
    Call<BaseModel> article_delete(@Query("id") int id);
}
