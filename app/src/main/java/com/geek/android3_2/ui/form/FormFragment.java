package com.geek.android3_2.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geek.android3_2.R;
import com.geek.android3_2.base.BaseFragment;
import com.geek.android3_2.common.Resource;
import com.geek.android3_2.data.models.Post;
import com.geek.android3_2.databinding.FragmentFormBinding;

import dagger.hilt.android.AndroidEntryPoint;

public class FormFragment extends BaseFragment<FragmentFormBinding> {

    private FormViewModel viewModel;
    private Post post = new Post();
    private final int userId = 9;
    private final int groupId = 93;
    private int postId;

    @Override
    protected FragmentFormBinding bind() {
        return FragmentFormBinding.inflate(getLayoutInflater());

    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(requireActivity()).get(FormViewModel.class);
//        userId = FormFragmentArgs.fromBundle(getArguments()).getId();

        binding.btnCreate.setOnClickListener(v -> {
            Post post = new Post(
                    binding.etTitle.getText().toString(),
                    binding.etContent.getText().toString(),
                    userId, groupId);
            viewModel.createPost(post);
            navController.navigate(R.id.postsFragment);
        });

        post = (Post) getArguments().getSerializable("post");
        if (post != null) {
            binding.etTitle.setText(post.getTitle());
            binding.etContent.setText(post.getContent());
            binding.etUser.setText("" + post.getUser());
            binding.etGroup.setText("" + post.getGroup());
            postId = post.getId();
        }
    }

    @Override
    protected void setupObservers() {

        viewModel.createPost(post).observe(getViewLifecycleOwner(), new Observer<Resource<Post>>() {
            @Override
            public void onChanged(Resource<Post> postResource) {
                switch (postResource.status) {
                    case LOADING: {
                        binding.formProgressBar.setVisibility(View.VISIBLE);
                        break;
                    }
                    case SUCCESS: {
                        binding.formProgressBar.setVisibility(View.GONE);
                        Log.e("TAG", "onChanged: " + postResource.data.toString());
                        break;
                    }
                    case ERROR: {
                        binding.formProgressBar.setVisibility(View.INVISIBLE);
                        Log.e("TAG", "onChanged: " + postResource.message);
                        break;
                    }
                }
            }
        });

        binding.btnUpgrade.setOnClickListener(v -> {
            viewModel.upgradePost(postId, post)
                    .observe(getViewLifecycleOwner(), new Observer<Resource<Post>>() {
                        @Override
                        public void onChanged(Resource<Post> postResource) {
                            switch (postResource.status) {
                                case LOADING: {
                                    binding.formProgressBar.setVisibility(View.VISIBLE);
                                    break;
                                }
                                case SUCCESS: {
                                    binding.etTitle.getText();
                                    binding.etContent.getText();
                                    binding.formProgressBar.setVisibility(View.GONE);
                                    Log.e("TAG", "onChanged: " + postResource.data.toString());
                                    break;
                                }
                                case ERROR: {
                                    binding.formProgressBar.setVisibility(View.GONE);
                                    Log.e("TAG", "onChanged: " + postResource.message);
                                    break;
                                }
                            }
                        }
                    });
            navController.navigate(R.id.postsFragment);
        });

    }
}