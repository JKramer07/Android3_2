package com.geek.android3_2.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.geek.android3_2.App;
import com.geek.android3_2.common.Resource;
import com.geek.android3_2.data.models.Post;
import com.geek.android3_2.data.remote.HerokuApi;
import com.geek.android3_2.domain.repositories.HerokuRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HerokuRepositoryImpl implements HerokuRepository {

    private HerokuApi herokuApi;

    @Inject
    public HerokuRepositoryImpl(HerokuApi api) {
        this.herokuApi = api;
    }

    public MutableLiveData<Resource<Post>> upgradePost(int id, Post post){
        MutableLiveData<Resource<Post>> posts = new MutableLiveData<>();
        posts.setValue(Resource.loading(null));
        herokuApi.upgradePost(id, post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null){
                    posts.setValue(Resource.success(response.body()));
                } else {
                    posts.setValue(Resource.error(response.errorBody().toString(), null));
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                posts.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return posts;
    }

    public MutableLiveData<Resource<Post>> createPost(Post post){
        MutableLiveData<Resource<Post>> posts = new MutableLiveData<>();
        posts.setValue(Resource.loading(null));
        herokuApi.createPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null){
                    posts.setValue(Resource.success(response.body()));
                } else {
                    posts.setValue(Resource.error(response.errorBody().toString(), null));
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                posts.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return posts;
    }

    public MutableLiveData<Resource<List<Post>>> getPosts(){
        MutableLiveData<Resource<List<Post>>> postList = new MutableLiveData<>();
        postList.setValue(Resource.loading(null));
        herokuApi.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null){
                    postList.setValue(Resource.success(response.body()));
                } else {
                    postList.setValue(Resource.error(response.errorBody().toString(), null));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postList.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return postList;
    }



    public MutableLiveData<Resource<Post>> deletePost(int id) {
        MutableLiveData<Resource<Post>> deleteLiveData = new MutableLiveData<>();
        deleteLiveData.setValue(Resource.loading(null));
        herokuApi.deletePost(id).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null){
                    deleteLiveData.setValue(Resource.success(response.body()));
                } else {
                    deleteLiveData.setValue(Resource.error(response.errorBody().toString(), null));
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                deleteLiveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return deleteLiveData;
    }
}
