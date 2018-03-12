package com.example.ahmet.news;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.UriMatcher;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.ahmet.news.Adapter.NewsArticlesAdapter;
import com.example.ahmet.news.model.Article;
import com.example.ahmet.news.model.NewsApiCustomer;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView newsRecycler;
    SwipeRefreshLayout swipeRefreshLayout ;
    NewsArticlesAdapter articlesAdapter;
    List<Article> artic;
    ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar()!=null){
            getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        }

        imageButton= (ImageButton)findViewById(R.id.action_bar_search);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        newsRecycler=findViewById(R.id.newsRecycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        articlesAdapter = new NewsArticlesAdapter();
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        newsRecycler.setLayoutManager(mLayoutManager);
        newsRecycler.setAdapter(articlesAdapter);
        getArticlesFromHurriyet();
        newsRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(this, newsRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        int id=newsRecycler.getChildLayoutPosition(view);
                        String ID = artic.get(id).getId();
                        Intent intent = new Intent(MainActivity.this,Newsdescription.class);
                        intent.putExtra("ID",ID);
                        intent.putExtra("Title", artic.get(id).getTitle());
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        getArticlesFromHurriyet();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        android.support.v7.app.AlertDialog.Builder builder= new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
    }
    public void getArticlesFromHurriyet(){
        NewsApiCustomer.getArticles(new ResponseListener<List<Article>>() {
        @Override
            public void onResponse(List<Article> response) {
                articlesAdapter.setItems(response);
                artic = response;
            }
            @Override
            public void onError(String error) {
                Log.d("Error",error);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this);
            alertbox.setIcon(R.drawable.icons8);
            alertbox.setTitle("Uygulamadan çıkamak istediğinize emin misiniz?");
            alertbox.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(MainActivity.this, "fffff", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            alertbox.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // Nothing will be happened when clicked on no button
                    // of Dialog
                }
            });
            alertbox.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
