package com.geek.android3_2.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.geek.android3_2.App;
import com.geek.android3_2.data.models.Films;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GhibliRepository {

//    public MutableLiveData<List<Films>> getFilms(){
//        MutableLiveData<List<Films>> liveData = new MutableLiveData<>();
//        App.api.getFilms().enqueue(new Callback<List<Films>>() {
//            @Override
//            public void onResponse(Call<List<Films>> call, Response<List<Films>> response) {
//                if (response.isSuccessful() && response.body() != null){
//                    liveData.setValue(response.body());
//                } else {
//                    liveData.setValue(null);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Films>> call, Throwable t) {
//                liveData.setValue(null);
//            }
//        });
//        return liveData;
//    }
}
