package com.example.testapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.makeramen.roundedimageview.RoundedImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends FirebaseRecyclerAdapter<ModelRec,MyAdapter.myViewholder>{
    boolean change=true;
    boolean isLoading=false;
    public MyAdapter(@NonNull FirebaseRecyclerOptions<ModelRec> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull ModelRec model) {
//        if(isLoading)
//        {
//            holder.s.startShimmerAnimation();
//            holder.s.setVisibility(View.VISIBLE);
//            holder.itemView.setVisibility(View.GONE);
//        }
//        else
//        {
//            holder.s.stopShimmerAnimation();
//            holder.s.setVisibility(View.GONE);
//            holder.itemView.setVisibility(View.VISIBLE);
//        }
        Glide.with(holder.img.getContext()).load(model.getPimage()).into(holder.img);
        holder.title.setText(model.getTitle());
        holder.price.setText(model.getPrice());
        holder.card.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.animation_rec));

    }
    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ui,parent,false);    // view convert row_ui to view
        return new myViewholder(view);



    }

    class myViewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
////        RoundedImageView img;
//        CircleImageView img;
        TextView title,price;
        CardView card;
        ShimmerFrameLayout s;
//        RecyclerView r;
                public myViewholder(@NonNull View itemView) {
                    super(itemView);
//                    r=itemView.findViewById(R.id.recView);
                    s=itemView.findViewById(R.id.shimmer);

                   img=itemView.findViewById(R.id.img1);
                    title=itemView.findViewById(R.id.rectxt1);
                    price=itemView.findViewById(R.id.rectxt2);
                    card=itemView.findViewById(R.id.card);
        }
    }
}
