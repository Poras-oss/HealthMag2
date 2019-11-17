package com.mag.healthmag;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prof.rssparser.Article;

import java.util.ArrayList;
import java.util.Random;

class MyAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    //Category Adapter
     FragmentActivity fragmentActivity;
     ArrayList<Article> feeds;
    public MyAdapter(FragmentActivity activity, ArrayList<Article> arrayList) {
        this.fragmentActivity = activity;
        this.feeds = arrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        final Article posts = feeds.get(position);
        int[] androidColors = fragmentActivity.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        Glide.with(fragmentActivity).load(posts.getImage()).placeholder(R.drawable.ic_launcher_background).into(holder.img);
        String description = posts.getDescription();
        if (description.length() < 250){
            description = description;
        }else{
            description = description.substring(0,250)+"...";
        }
        holder.title.setText(posts.getTitle());
        description = description.replaceAll("\\<.*?\\>", "");
        holder.date.setText(description);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragmentActivity,webview.class);
                intent.putExtra("url",posts.getLink());
                fragmentActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return feeds == null ? 0 : feeds.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        Context context;
        public ImageView img;
        public TextView title;
        public TextView date;
        public RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.feedimg);
            title = (TextView)itemView.findViewById(R.id.m_title);
            date = (TextView)itemView.findViewById(R.id.desc);
            layout = (RelativeLayout) itemView.findViewById(R.id.magazine);
        }
    }
}
