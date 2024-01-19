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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Expandfragment extends Fragment {
    private TextView tv1, tv2, tv3;
    private RoundedImageView img;

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

////        assert getArguments() != null;
////        tv1.setText(getArguments().getString("title"));
////        tv2.setText(getArguments().getString("price"));
////        tv3.setText(getArguments().getString("time"));
////        img.setImageURI(Uri.parse(getArguments().getString("img")));
//
//        img.setImageResource(getActivity().getIntent().getIntExtra("image",0));
//        tv1.setText(getActivity().getIntent().getStringExtra("title"));
//        tv2.setText(getActivity().getIntent().getStringExtra("price"));
//        tv3.setText(getActivity().getIntent().getStringExtra("time"));

        Bundle args = getArguments();
        if (args != null) {
            String imgUrl = args.getString("img");
            String title = args.getString("title");
            String price = args.getString("price");
            String time = args.getString("time");

            tv1.setText(title);
            tv2.setText(price);
            tv3.setText(time);
            Glide.with(img.getContext()).load(imgUrl).into(img);
        } else {
            // Handle the case where arguments are not present
        }

        return v;
    }
}
