package com.mag.healthmag;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.mag.healthmag.categoryFragments.Family;
import com.mag.healthmag.categoryFragments.Fitness;
import com.mag.healthmag.categoryFragments.Food_Nutrition;
import com.mag.healthmag.categoryFragments.Happy_Life;
import com.mag.healthmag.categoryFragments.Health;
import com.mag.healthmag.categoryFragments.Healthy_Home;
import com.mag.healthmag.categoryFragments.Healthy_Mind;
import com.mag.healthmag.categoryFragments.LifeStyle;
import com.mag.healthmag.categoryFragments.News;
import com.mag.healthmag.categoryFragments.Pets;
import com.mag.healthmag.categoryFragments.Relationship;
import com.mag.healthmag.categoryFragments.Sex;
import com.mag.healthmag.categoryFragments.Sleep;
import com.mag.healthmag.categoryFragments.illness;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Categories extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;



    public Categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);

        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Food_Nutrition(), "Food & Nutrition");
        adapter.addFragment(new Health(), "Health");
        adapter.addFragment(new Fitness(), "Fitness");
        adapter.addFragment(new Healthy_Home(), "Healthy home");
        adapter.addFragment(new Healthy_Mind(), "Healthy Mind");
        adapter.addFragment(new Family(), "Family");
        adapter.addFragment(new Pets(), "Pets");
        adapter.addFragment(new LifeStyle(), "LifeStyle");
        adapter.addFragment(new Sex(), "Sex");
        adapter.addFragment(new Relationship(), "Relationship");
        adapter.addFragment(new News(), "News");
        adapter.addFragment(new Sleep(), "Sleep");
        adapter.addFragment(new illness(), "illness");
        adapter.addFragment(new Happy_Life(), "Happy Life");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }




}
