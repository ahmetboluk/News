package com.example.ahmet.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.ahmet.news.model.Article;
import com.example.ahmet.news.model.NewsApiCustomer;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class Newsdescription extends AppCompatActivity {

    ImageView imageView;
    TextView descriptiontextView;
    TextView titletextView;
    android.support.v7.app.ActionBar actionBar;
    Article singleartic;
 


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdescription);

        Intent intent = getIntent();

        actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(intent.getStringExtra("Title"));
        }

        imageView = (ImageView)findViewById(R.id.imageView);
        descriptiontextView = (TextView) findViewById(R.id.textView);
        titletextView = (TextView) findViewById(R.id.textView1);



        String ID = intent.getStringExtra("ID");
        NewsApiCustomer.getSingleArticle(ID,new ResponseListener<Article>() {
            @Override
            public void onResponse(Article response) {
                singleartic = response;
                String url ;
                if(singleartic.getFiles().isEmpty()) {
                    url = null;
                }else{
                    url = singleartic.getFiles().get(0).getFileUrl();
                }
                    Picasso.with(Newsdescription.this)
                            .load(url)
                            .placeholder(R.drawable.web_hi_res_512)
                            .into(imageView);
                descriptiontextView.setText(Html.fromHtml(singleartic.getText()));
                titletextView.setText(singleartic.getTitle());

            }
            @Override
            public void onError(String error) {
                Log.d("Error",error);
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
