package com.geek.android3_2.ui.posts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geek.android3_2.App;
import com.geek.android3_2.common.Resource;
import com.geek.android3_2.data.models.Post;
import com.geek.android3_2.data.repositories.HerokuRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PostsViewModel extends ViewModel {

    private HerokuRepositoryImpl herokuRepository;

    @Inject
    public PostsViewModel(HerokuRepositoryImpl repository){
        this.herokuRepository = repository;
    }

    LiveData<Resource<List<Post>>> getPosts(){
        return herokuRepository.getPosts();
    }

    LiveData<Resource<Post>> deletePost(int id){
        return herokuRepository.deletePost(id);
    }
}
