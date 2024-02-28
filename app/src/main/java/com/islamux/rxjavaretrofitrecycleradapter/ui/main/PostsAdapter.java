package com.islamux.rxjavaretrofitrecycleradapter.ui.main;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.islamux.rxjavaretrofitrecycleradapter.R;
import com.islamux.rxjavaretrofitrecycleradapter.pojo.PostModel;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private ArrayList<PostModel> postsList = new ArrayList<>();


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.titleTV.setText(postsList.get(position).getTitle());
        holder.userIdTV.setText(postsList.get(position).getUserId()+"");
        holder.bodyTV.setText(postsList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public void setPostsList(ArrayList<PostModel> postsList) {
        this.postsList = postsList;
        notifyDataSetChanged();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV, userIdTV, bodyTV;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            userIdTV = itemView.findViewById(R.id.userIdTV);
            bodyTV = itemView.findViewById(R.id.bodyTV);


        }
    }
}