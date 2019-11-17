package com.mag.healthmag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Main Navigation
        navigationView = (BottomNavigationView)findViewById(R.id.bottom_nav);
        loadFragment(new Home());
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()){
                    case R.id.home_nav:
                        fragment = new Home();
                        loadFragment(fragment);
                        return true;
                    case R.id.disc_nav:
                        fragment = new Categories();
                        loadFragment(fragment);
                        return true;

                }
                return false;
            }
        });

    }
    //loads Fragment ... bottom navigation
    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bottom_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
