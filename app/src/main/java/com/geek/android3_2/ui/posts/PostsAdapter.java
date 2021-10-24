package com.geek.android3_2.ui.posts;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.android3_2.data.models.Post;
import com.geek.android3_2.databinding.ItemPostsRvBinding;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<Post> postList = new ArrayList<>();
    private OnPostsClick listener;

    public PostsAdapter() {
    }

    public void setListener(OnPostsClick listener){
        this.listener = listener;
    }

    public void setPosts(List<Post> postList){
        this.postList.addAll(postList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostsRvBinding binding = ItemPostsRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(postList.get(position));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void deleteItem(Post post) {
        postList.remove(post);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemPostsRvBinding binding;
        public ViewHolder(@NonNull ItemPostsRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Post post) {
            binding.tvPostTitle.setText(post.getTitle());
            binding.tvPostContent.setText(post.getContent());
            binding.tvPostUser.setText(String.valueOf(post.getUser()));
            binding.tvPostGroup.setText(" "+post.getGroup());
            itemView.setOnClickListener(v -> {
                listener.onClick(post);
            });
            itemView.setOnLongClickListener(v -> {
                listener.onLongClick(post);
                return true;
            });
        }
    }
}

interface OnPostsClick {
    void onClick(Post post);
    void onLongClick(Post post);
}