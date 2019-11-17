package com.mag.healthmag;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;
import java.util.Random;

public class feeds extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private String baseUrl = "https://www.lifehack.org/feed";
    private ArrayList<Article> f;
    private String url;
    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feeds);

        url = getIntent().getStringExtra("url");
        category = getIntent().getStringExtra("category");

        toolbar = (Toolbar)findViewById(R.id.app_bar_layout_feed);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(category);


        if(!isConnected(feeds.this)) buildDialog(feeds.this).show();
        else {
            start(url);
            progressDialog = new ProgressDialog(feeds.this);
            progressDialog.setMessage("Fetching Posts..."); // Setting Message
            //  progressDialog.setTitle("ProgressDialog"); // Setting Title
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
            progressDialog.show(); // Display Progress Dialog
            progressDialog.setCancelable(false);
        }


    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }

    //----------------------Fetching feeds into RecyclerVIew------------------------------

    private void start(String murl){

        Parser parser = new Parser();
        parser.execute(murl);
        parser.onFinish(new Parser.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(ArrayList<Article> arrayList) {
              Toast.makeText(getApplication(),"success",Toast.LENGTH_SHORT).show();
                Log.d("HOLA", "onTaskCompleted: "+arrayList.toString());
                recyclerView = (RecyclerView) findViewById(R.id.feedsRecycler);
                layoutManager = new LinearLayoutManager(getBaseContext());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new FAdapter(arrayList,getApplicationContext());

                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onError() {
           Toast.makeText(getApplication(),"failed",Toast.LENGTH_SHORT).show();
                start(baseUrl);
                progressDialog.dismiss();
            }
        });
    }

    private class FAdapter extends RecyclerView.Adapter<FAdapter.mviewHolder> {
        private ArrayList<Article> feeds;
        private Context context;


        public FAdapter(ArrayList<Article> arrayList, Context applicationContext){
            this.context = applicationContext;
            this.feeds = arrayList;
        }
        @NonNull
        @Override
        public mviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_card,parent,false);
            return new mviewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull mviewHolder holder, final int position) {
            final Article posts = feeds.get(position);
            int[] androidColors = getResources().getIntArray(R.array.androidcolors);
            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            Glide.with(context).load(posts.getImage()).placeholder(R.drawable.ic_launcher_background).into(holder.img);
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
                    Intent intent = new Intent(getBaseContext(),webview.class);
                    intent.putExtra("url",posts.getLink());
                    startActivity(intent);
                }
            });

        }


        @Override
        public int getItemCount() {
            return feeds == null ? 0 : feeds.size();
        }

        public class mviewHolder extends RecyclerView.ViewHolder{

            public ImageView img;
            public TextView title;
            public TextView date;
            public RelativeLayout layout;

            public mviewHolder(@NonNull View itemView) {
                super(itemView);
                img = (ImageView)itemView.findViewById(R.id.feedimg);
                title = (TextView)itemView.findViewById(R.id.m_title);
                date = (TextView)itemView.findViewById(R.id.desc);
                layout = (RelativeLayout) itemView.findViewById(R.id.magazine);
            }
        }
    }
}
