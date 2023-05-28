package com.example.testapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link p_details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class p_details extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public p_details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment p_details.
     */
    // TODO: Rename and change types and number of parameters
    public static p_details newInstance(String param1, String param2) {
        p_details fragment = new p_details();
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

    CardView c;         // creating object of that source where we are clicked
    tempfrg t;
    Button b1,b2;// creating reference of fragment we are going to switch
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_p_details, container, false);
       t=new tempfrg();
       b2=v.findViewById(R.id.cartbtn);
       b1=v.findViewById(R.id.buy);
       c=v.findViewById(R.id.materialCardView);
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
               ft.replace(R.id.frm_layout,t).commit();
           }
       });
       tempfrg tmf=new tempfrg();
       c.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FragmentTransaction ft1=getActivity().getSupportFragmentManager().beginTransaction();
               ft1.replace(R.id.frm_layout,tmf).commit();
           }
       });
//       c=v.findViewById(R.id.materialCardView);       // typecasting our click source
//       c.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
//               ft.replace(R.id.frm_layout,t).commit();
//           }
//       });
       return v;
    }
}