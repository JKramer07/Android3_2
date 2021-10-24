package com.geek.android3_2.ui.posts;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.geek.android3_2.R;
import com.geek.android3_2.base.BaseFragment;
import com.geek.android3_2.common.Resource;
import com.geek.android3_2.data.models.Post;
import com.geek.android3_2.databinding.FragmentPostsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PostsFragment extends BaseFragment<FragmentPostsBinding> implements OnPostsClick{

    private PostsViewModel viewModel;
    private PostsAdapter adapter;

    public PostsFragment() {
    }

    @Override
    protected FragmentPostsBinding bind() {
        return FragmentPostsBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(PostsViewModel.class);
    }

    @Override
    protected void setupObservers() {
        viewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> postResource) {
                switch (postResource.status){
                    case SUCCESS:{
                        binding.progressPosts.setVisibility(View.GONE);
                        adapter.setPosts(postResource.data);
                        break;
                    }
                    case ERROR:{
                        binding.tvError.setText(postResource.message);
                        break;
                    }
                    case LOADING:{
                        binding.progressPosts.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            openFormFragment(null);
        });

    }

    private void initRecycler() {
        adapter = new PostsAdapter();
        adapter.setListener(this);
        binding.rvPosts.setAdapter(adapter);
    }

    @Override
    public void onClick(Post post) {
        openFormFragment(post);
    }

    private void openFormFragment(Post post) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("post", post);
        navController.navigate(R.id.formFragment,bundle);
    }

    @Override
    public void onLongClick(Post post) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setMessage("Delete item?")
                .setTitle("Warning!")
                .setNegativeButton("No", (dialog1, which) -> {
                    dialog1.cancel();
                })
                .setPositiveButton("Yes", (dialog1, which) -> {
                    viewModel.deletePost(post.getId());
                    adapter.deleteItem(post);
                    Toast.makeText(requireContext(), "Item is deleted", Toast.LENGTH_SHORT).show();
                })
                .show();
    }
}