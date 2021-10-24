package com.geek.android3_2;

import android.app.Application;

import com.geek.android3_2.data.remote.GhibliApi;
import com.geek.android3_2.data.remote.HerokuApi;
import com.geek.android3_2.data.remote.RetrofitClient;
import com.geek.android3_2.data.repositories.GhibliRepository;
import com.geek.android3_2.data.repositories.HerokuRepositoryImpl;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
