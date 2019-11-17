package com.mag.healthmag;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFrag extends Fragment {

    private TabLayout.Tab c;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Toolbar toolbar;

    public CategoryFrag() {
        // Required empty public constructor

    }






    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = (Toolbar)view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Healthmag");
        toolbar.setNavigationIcon(R.drawable.home);
        //------------Recycler View------------------------------------------------------------------------
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.categoryRecycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new mAdapter();
        recyclerView.setAdapter(adapter);
       //------------------------------------------------------------------------------------------------

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);

    }


        //------------------------INNER ADAPTER CLASS--------------------------------------------------------------------

    public class mAdapter extends RecyclerView.Adapter<mAdapter.mViewHolder> {

        private int[] src = {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};


        @NonNull
        @Override
        public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.big_mag,parent,false);
            return new mViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
            holder.imageView.setImageResource(src[position]);

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),feeds.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return src.length;
        }

        public class mViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public LinearLayout linearLayout;
            public mViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = (ImageView)itemView.findViewById(R.id.bigMag);
                linearLayout = (LinearLayout)itemView.findViewById(R.id.magmag);
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------
}
