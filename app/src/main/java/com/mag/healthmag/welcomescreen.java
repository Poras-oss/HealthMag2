package com.mag.healthmag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class welcomescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomescreen);

        final Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                    Intent i = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);

                    finish();
                }catch (Exception e){

                }
            }
        };

        thread.start();
    }
}
