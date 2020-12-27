package com.example.dailynews.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dailynews.MainActivity;
import com.example.dailynews.Model.Article;
import com.example.dailynews.R;
import com.example.dailynews.TwoActivity;

import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ListHolder>{
    FragmentActivity activity;
    List<Article> dataList1;

    public MainListAdapter(FragmentActivity mainActivity, List<Article> dataList1) {
        activity = mainActivity;
        this.dataList1 = dataList1;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.mainview,parent,false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, final int position) {

        Glide.with(activity).load(dataList1.get(position).getUrlToImage()).into(holder.image);
        holder.title.setText(dataList1.get(position).getTitle());
        holder.pub.setText(dataList1.get(position).getPublishedAt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TwoActivity.class);
                intent.putExtra("title",dataList1.get(position).getTitle());
                intent.putExtra("image",dataList1.get(position).getUrlToImage());
                intent.putExtra("des",dataList1.get(position).getDescription());
                intent.putExtra("content",dataList1.get(position).getContent());
                intent.putExtra("url",dataList1.get(position).getUrl());
                intent.putExtra("publishat",dataList1.get(position).getPublishedAt());
                intent.putExtra("author",dataList1.get(position).getAuthor());
                activity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList1.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title,pub;
        public ListHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            pub = itemView.findViewById(R.id.publishedat);
        }
    }
}
