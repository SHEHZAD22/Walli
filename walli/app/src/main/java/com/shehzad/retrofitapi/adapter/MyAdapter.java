package com.shehzad.retrofitapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shehzad.retrofitapi.FullImageActivity;
import com.shehzad.retrofitapi.model.Hits;
import com.shehzad.retrofitapi.R;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Hits> imageList;
    Context context;

    public MyAdapter(List<Hits> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Hits model = imageList.get(position);
        holder.likes.setText("Likes: " + model.getLikes());
        holder.views.setText("Views: " + model.getViews());

        Glide.with(context).load(model.getSecondImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.pb.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.pb.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imageView);

        holder.itemView.setOnClickListener(view ->  {
                Toast.makeText(context, "Tags: " + model.getTags(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, FullImageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("image", model.getSecondImage());
                context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        if(imageList != null) return imageList.size();
        else return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ProgressBar pb;
        ImageView imageView;
        TextView likes,views;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            likes = itemView.findViewById(R.id.likes);
            views = itemView.findViewById(R.id.views);
            pb = itemView.findViewById(R.id.progressbar);
        }

    }
}
