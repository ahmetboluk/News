package com.example.ahmet.news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmet on 27.02.2018.
 */

public class SearchItem {
    @SerializedName("Count")
    @Expose
    private Integer count;
    @SerializedName("List")
    @Expose
    private List<Article> list = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Article> getList() {
        return list;
    }

    public void setList(List<Article> list) {
        this.list = list;
    }
}