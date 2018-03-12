package com.example.ahmet.news;

/**
 * Created by ahmet on 19.02.2018.
 */

public interface ResponseListener<T> {
    void onResponse(T response);
    void onError(String error);
}
