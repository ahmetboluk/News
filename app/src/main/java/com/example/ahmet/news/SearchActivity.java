package com.example.ahmet.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ahmet.news.Adapter.NewsArticlesAdapter;
import com.example.ahmet.news.model.NewsApiCustomer;
import com.example.ahmet.news.model.SearchItem;

import java.sql.Time;

public class SearchActivity extends AppCompatActivity {

    EditText editText ;
    ImageButton imageButton;
    RecyclerView searchRecycler;
    NewsArticlesAdapter articlesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    android.support.v7.app.ActionBar actionBar;
    Intent intent;
    SearchItem searchItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        intent = new Intent(getApplicationContext(),MainActivity.class);

        editText = (EditText) findViewById(R.id.keyword_Text);
        imageButton= (ImageButton)findViewById(R.id.action_search);

        actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Search");
        }


        articlesAdapter = new NewsArticlesAdapter();
        searchRecycler=(RecyclerView) findViewById(R.id.search_Recycler);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        searchRecycler.setLayoutManager(layoutManager);
        searchRecycler.setAdapter(articlesAdapter);
        searchRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(this, searchRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        int id=searchRecycler.getChildLayoutPosition(view);
                        String ID = searchItem.getList().get(id).getId();
                        Intent intent = new Intent(SearchActivity.this,Newsdescription.class);
                        intent.putExtra("ID",ID);
                        intent.putExtra("Title",searchItem.getList().get(id).getTitle());
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsApiCustomer.getSearch(editText.getText().toString(), new ResponseListener<SearchItem>() {
                    @Override
                    public void onResponse(SearchItem response) {
                        if (response.getList().isEmpty())
                        {
                            Toast.makeText(SearchActivity.this, "İlgili haber yok", Toast.LENGTH_SHORT).show();
                        }else {
                            articlesAdapter.setItems(response.getList());
                            searchItem = response;
                            Log.d("OFFFFFFFF", response.getList().get(0).getText());
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        }
                    }
                    @Override
                    public void onError(String error) {
                        Toast.makeText(SearchActivity.this, "hata hata", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
