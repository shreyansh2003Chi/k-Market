package com.example.testapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class home_frgmnt extends Fragment {
    RecyclerView rv;
    MyAdapter myadapter;
    SearchView search;
//    ShimmerFrameLayout shimmer;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public home_frgmnt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add.
     */
    // TODO: Rename and change types and number of parameters
    public static add newInstance(String param1, String param2) {
        add fragment = new add();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//         Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_frgmnt, container, false);
//        shimmer=view.findViewById(R.id.shimmer);
//        shimmer.setVisibility(View.VISIBLE);
//        shimmer.startShimmerAnimation();
        search=view.findViewById(R.id.searchView);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });
        rv= view.findViewById(R.id.recView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<ModelRec> options =
                new FirebaseRecyclerOptions.Builder<ModelRec>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products"), ModelRec.class)
                        .build();
        myadapter=new MyAdapter(options,this.getContext());
        rv.setAdapter(myadapter);
//         shimmer.stopShimmerAnimation();
//         shimmer.setVisibility(View.GONE);
        return view;
    }

    private void filterList(String text) {
        FirebaseRecyclerOptions<ModelRec> options =
                new FirebaseRecyclerOptions.Builder<ModelRec>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").orderByChild("title").startAt(text).endAt(text+"\uf8ff"), ModelRec.class)
                        .build();

        myadapter=new MyAdapter(options,this.getContext());
        myadapter.startListening();
        rv.setAdapter(myadapter);
    }


    public void onStart()
    {

        super.onStart();

        myadapter.startListening();

    }
    public void onStop()
    {
        super.onStop();
        myadapter.stopListening();
    }
}