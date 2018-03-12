package com.example.ahmet.news.api;

import com.example.ahmet.news.model.Article;
import com.example.ahmet.news.model.SearchItem;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by enesignecirdc on 09/02/2018.
 */

public interface HurriyetService {
    @GET("v1/articles")
    Call<List<Article>> listArticles(@Query("apikey") String apikey);
    @GET("v1/articles/{id}")
    Call<Article> getArticle(@Path("id") String id, @Query("apikey") String apikey);
    @GET("v1/search/{keyword}")
    Call<SearchItem> getSearchItem(@Path("keyword") String keyword, @Query("apikey") String apikey);
}
