package com.geek.android3_2.data.remote;

import com.geek.android3_2.common.Resource;
import com.geek.android3_2.data.models.Films;
import com.geek.android3_2.data.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HerokuApi {

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("post/{postId}")
    Call<Post> getOnePost(
            @Path("postId") int id);

    @PUT("posts/{postId}")
    Call<Post> upgradePost(
            @Path("postId") int id,
            @Body Post post);

    @DELETE("posts/{postId}")
    Call<Post> deletePost(
            @Path("postId") int id);
}
