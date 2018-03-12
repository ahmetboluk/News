package com.example.ahmet.news.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmet.news.R;
import com.example.ahmet.news.RelativeDateCalculator;
import com.example.ahmet.news.model.Article;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ahmet on 21.02.2018.
 */

public class NewsArticlesAdapter extends RecyclerView.Adapter<NewsArticlesAdapter.MyViewHolder> {
    private List<Article> articleList;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_row_layout, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.textView.setText(article.getTitle());
        Log.i("TİME",article.getModifiedDate());
        String time =null;
        Date date;

        String[] formats = new String[] {
                "yyyy-MM-dd",
                "yyyy-MM-dd HH:mm",
                "yyyy-MM-dd HH:mmZ",
                "yyyy-MM-dd HH:mm:ss.SSS'Z'",
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        };
        if (time == null ){
            for (String format : formats) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                try {
                    date = sdf.parse(article.getModifiedDate());
                    time =RelativeDateCalculator.getTimeAgo(date.getTime());
                    Log.i("TİME",time);
                } catch (ParseException e) {
                    time=null;
                }
            }
        }
        holder.textView2.setText(time);
        holder.imageView.setImageResource(R.drawable.web_hi_res_512);
        if (article.getFiles().size() > 0) {
            Picasso.with(holder.context)
                    .load(article.getFiles().get(0).getFileUrl())
                    .into(holder.imageView);
        }
    }
    @Override
    public int getItemCount() {
        if (articleList == null) {
            return 0;
        }
        return articleList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView textView;
        private TextView textView2;
        private ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title);
            textView2 = (TextView) itemView.findViewById(R.id.time);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            context = itemView.getContext();
        }
    }
    public void setItems(List<Article> articles) {
        this.articleList = articles;
        notifyDataSetChanged();
    }
}
