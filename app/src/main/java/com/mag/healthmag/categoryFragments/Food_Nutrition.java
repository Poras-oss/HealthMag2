package com.mag.healthmag.categoryFragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mag.healthmag.R;
import com.mag.healthmag.feeds;

/**
 * A simple {@link Fragment} subclass.
 */
public class Food_Nutrition extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;




    public Food_Nutrition() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //------------Recycler View------------------------------------------------------------------------
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.categoryRecycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new foodAdapter();
        recyclerView.setAdapter(adapter);
        //------------------------------------------------------------------------------------------------

    }

    private class foodAdapter extends RecyclerView.Adapter<foodAdapter.FoodHolder> {
        private int[] magimg = {R.drawable.pinch_of_yum,R.drawable.minimalist_baker,R.drawable.fork_bacon,R.drawable.naturally_ella,R.drawable.serious_eats};
        private String[] url = {"https://www.betternutrition.com/.rss/full/","http://blog.skinnymint.com/category/healthy-recipes/feed/","https://www.quickanddirtytips.com/nutrition-diva/feed","https://www.treehugger.com/feeds/tag/food-miles/","https://www.thekitchn.com/main.rss"};
        private String[] name = {"Better Nutrition","SkinnyMint","QuickAndDirtyTips","TreeHugger","TheKitchn"};
        @NonNull
        @Override
        public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.big_mag,parent,false);
            return new FoodHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull FoodHolder holder, final int position) {
            holder.imageView.setImageResource(magimg[position]);
            holder.name.setText(name[position]);
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), feeds.class);
                    intent.putExtra("url",url[position]);
                    intent.putExtra("category","Food&Nutrition");
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return magimg.length;
        }

        public class FoodHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public LinearLayout linearLayout;
            public TextView name;
            public FoodHolder(@NonNull View itemView) {
                super(itemView);
                imageView = (ImageView)itemView.findViewById(R.id.bigMag);
                linearLayout = (LinearLayout)itemView.findViewById(R.id.magmag);
                name =(TextView)itemView.findViewById(R.id.magText);
            }
        }
    }

}
