package com.example.testapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends FirebaseRecyclerAdapter<ModelRec,MyAdapter.myViewholder>{
    public MyAdapter(@NonNull FirebaseRecyclerOptions<ModelRec> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull ModelRec model) {
        Glide.with(holder.img.getContext()).load(model.getPimage()).into(holder.img);
        holder.title.setText(model.getTitle());
        holder.price.setText(model.getPrice());
        holder.time.setText(model.getCurrentTime());
        holder.card.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.animation_rec));

    }
    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ui,parent,false);    // view convert row_ui to view
        return new myViewholder(view);

    }

    class myViewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView title,price,time;
        CardView card;
        ShimmerFrameLayout s;

                public myViewholder(@NonNull View itemView) {
                    super(itemView);
                   img=itemView.findViewById(R.id.img1);
                    title=itemView.findViewById(R.id.rectxt1);
                    price=itemView.findViewById(R.id.rectxt2);
                    card=itemView.findViewById(R.id.card);
                    time=itemView.findViewById(R.id.currTime);
        }
    }
}
