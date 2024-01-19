//package com.example.testapp;
//
//import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.AnimationUtils;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.facebook.shimmer.ShimmerFrameLayout;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class MyAdapter extends FirebaseRecyclerAdapter<ModelRec,MyAdapter.myViewholder>{
//    Context context;
//    FirebaseRecyclerOptions<ModelRec> options;
//    public MyAdapter(@NonNull FirebaseRecyclerOptions<ModelRec> options, Context context) {
//        super(options);
//        this.options=options;
//        this.context=context;
//
//    }
//    @Override
//    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull ModelRec model) {
//
//        final ModelRec temp=options.getSnapshots().get(position);
//        Glide.with(holder.img.getContext()).load(model.getPimage()).into(holder.img);
//        holder.title.setText(model.getTitle());
//        holder.price.setText(model.getPrice());
//        holder.time.setText(model.getCurrentTime());
//        holder.card.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.animation_rec));
//
//        holder.card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i=new Intent(context,Expandfragment.class);
//                i.putExtra("img",temp.getPimage());
//                i.putExtra("title",temp.getTitle());
//                i.putExtra("price",temp.getPrice());
//                i.putExtra("time",temp.getCurrentTime());
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                context.startActivity(i);
//
//            }
//        });
//
//    }
//    @NonNull
//    @Override
//    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ui,parent,false);    // view convert row_ui to view
//        return new myViewholder(view);
//
//    }
//
//    class myViewholder extends RecyclerView.ViewHolder
//    {
//        CircleImageView img;
//        TextView title,price,time;
//        CardView card;
//        ShimmerFrameLayout s;
//
//                public myViewholder(@NonNull View itemView) {
//                    super(itemView);
//                   img=itemView.findViewById(R.id.img1);
//                    title=itemView.findViewById(R.id.rectxt1);
//                    price=itemView.findViewById(R.id.rectxt2);
//                    card=itemView.findViewById(R.id.card);
//                    time=itemView.findViewById(R.id.currTime);
//        }
//    }
//}

package com.example.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends FirebaseRecyclerAdapter<ModelRec, MyAdapter.MyViewHolder> {
    private Context context;

    public MyAdapter(@NonNull FirebaseRecyclerOptions<ModelRec> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull ModelRec model) {
        final ModelRec temp = getItem(position);
        Glide.with(holder.img.getContext()).load(model.getPimage()).into(holder.img);
        holder.title.setText(model.getTitle());
        holder.price.setText(model.getPrice());
        holder.time.setText(model.getCurrentTime());
        holder.card.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.animation_rec));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Expandfragment.class);
                i.putExtra("img", temp.getPimage());
                i.putExtra("title", temp.getTitle());
                i.putExtra("price", temp.getPrice());
                i.putExtra("time", temp.getCurrentTime());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ui, parent, false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView title, price, time;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img1);
            title = itemView.findViewById(R.id.rectxt1);
            price = itemView.findViewById(R.id.rectxt2);
            card = itemView.findViewById(R.id.card);
            time = itemView.findViewById(R.id.currTime);
        }
    }
}

