package com.geek.android3_2.data.remote;

import com.geek.android3_2.data.models.Films;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GhibliApi {

    @GET("films")
    Call<List<Films>> getFilms();
}
