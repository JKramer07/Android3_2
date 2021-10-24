package com.geek.android3_2.ui.form;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geek.android3_2.App;
import com.geek.android3_2.common.Resource;
import com.geek.android3_2.data.models.Post;
import com.geek.android3_2.data.repositories.HerokuRepositoryImpl;
import com.geek.android3_2.databinding.FragmentFormBinding;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FormViewModel extends ViewModel {

    private HerokuRepositoryImpl herokuRepository;

    @Inject
    public FormViewModel(HerokuRepositoryImpl repository){
        this.herokuRepository = repository;
    }

    LiveData<Resource<Post>> createPost(Post post){
        return herokuRepository.createPost(post);
    }

    LiveData<Resource<Post>> upgradePost(int id, Post post){
        return herokuRepository.upgradePost(id, post);
    }
}
