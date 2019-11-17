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
public class News extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;



    public News() {
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
        adapter = new newsadapter();
        recyclerView.setAdapter(adapter);
        //------------------------------------------------------------------------------------------------

    }

    private class newsadapter extends RecyclerView.Adapter<newsadapter.newsholder> {
        private int[] src = {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};

        @NonNull
        @Override
        public newsholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.big_mag,parent,false);
            return new newsholder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull newsholder holder, int position) {
            holder.imageView.setImageResource(src[position]);

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), feeds.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return src.length;
        }

        public class newsholder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public LinearLayout linearLayout;
            public newsholder(@NonNull View itemView) {
                super(itemView);
                imageView = (ImageView)itemView.findViewById(R.id.bigMag);
                linearLayout = (LinearLayout)itemView.findViewById(R.id.magmag);
            }
        }
    }

}
