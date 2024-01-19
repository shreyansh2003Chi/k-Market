//package com.example.testapp;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.fragment.app.Fragment;
//
//import com.example.testapp.R.id;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//public class Expandfragment extends Fragment {
//    TextView tv1,tv2,tv3;
//    CircleImageView img;
//    public Expandfragment() {
//    }
//    @SuppressLint("MissingInflatedId")
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View v=inflater.inflate(R.layout.fragment_expandfragment, container, false);
//        tv1=v.findViewById(id.titletxt);
//        tv2=v.findViewById(R.id.pricetxt);
//        tv3=v.findViewById(R.id.timetxt);
////        tv4=v.findViewById(R.id.dectxt);
//        img=v.findViewById(R.id.imgview);
//        assert getArguments() != null;
//        tv1.setText(getArguments().getString("title"));
//        tv2.setText(getArguments().getString("price"));
//        tv3.setText(getArguments().getString("time"));
////        tv4.setText(getArguments().getString("description"));
//        img.setImageURI(Uri.parse(getArguments().getString("img")));
//
//        return v;
//    }
//
//
//}

package com.example.testapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class Expandfragment extends Fragment {
    private TextView tv1, tv2, tv3;
    private CircleImageView img;

    public Expandfragment() {
    }

    public static Expandfragment newInstance(String img, String title, String price, String time) {
        Expandfragment fragment = new Expandfragment();
        Bundle args = new Bundle();
        args.putString("img", img);
        args.putString("title", title);
        args.putString("price", price);
        args.putString("time", time);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_expandfragment, container, false);
        tv1 = v.findViewById(R.id.titletxt);
        tv2 = v.findViewById(R.id.pricetxt);
        tv3 = v.findViewById(R.id.timetxt);
        img = v.findViewById(R.id.imgview);

        assert getArguments() != null;
        tv1.setText(getArguments().getString("title"));
        tv2.setText(getArguments().getString("price"));
        tv3.setText(getArguments().getString("time"));
        img.setImageURI(Uri.parse(getArguments().getString("img")));

        return v;
    }
}
