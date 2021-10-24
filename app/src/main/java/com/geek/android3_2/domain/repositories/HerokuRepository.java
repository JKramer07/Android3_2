package com.geek.android3_2.domain.repositories;

import androidx.lifecycle.MutableLiveData;

import com.geek.android3_2.common.Resource;
import com.geek.android3_2.data.models.Post;

import java.util.List;

public interface HerokuRepository {

    MutableLiveData<Resource<List<Post>>> getPosts();
}
