package com.example.ahmet.news.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.ahmet.news.Adapter.NewsArticlesAdapter;
import com.example.ahmet.news.MainActivity;
import com.example.ahmet.news.ResponseListener;
import com.example.ahmet.news.api.HurriyetService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmet on 21.02.2018.
 */

public class NewsApiCustomer {
    public static String URL = ("https://api.hurriyet.com.tr/");
    public static String APIKEY = ("4b987be8833a4ba8976a784c72b5b18d") ;

    static Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
    static HurriyetService service=retrofit.create(HurriyetService.class);

    public static void getArticles(final ResponseListener<List<Article>> responseListener) {
        Call<List<Article>> call = service.listArticles(APIKEY);
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, final Response<List<Article>> response) {
                responseListener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.d("Error", t.getMessage());
                responseListener.onError(t.getMessage());
            }
        });
    }
    public static void getSingleArticle(String id,final ResponseListener<Article> responseListener) {
        Call<Article> call = service.getArticle(id,APIKEY);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, final Response<Article> response) {
                Log.d("Response",response.body().getText());
                responseListener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Log.d("Error", t.getMessage());
                responseListener.onError(t.getMessage());
            }
        });
    }
    public static void getSearch(String keyword,final ResponseListener<SearchItem> responseListener) {
        Call<SearchItem> call = service.getSearchItem(keyword,APIKEY);
        call.enqueue(new Callback<SearchItem>() {
            @Override
            public void onResponse(Call<SearchItem> call, Response<SearchItem> response) {
                responseListener.onResponse(response.body());
  //              Log.d("Response",response.body().getList().get(0).getText());
            }

            @Override
            public void onFailure(Call<SearchItem> call, Throwable t) {
                Log.d("Error", t.getMessage());
                responseListener.onError(t.getMessage());

            }


        });
    }

}

