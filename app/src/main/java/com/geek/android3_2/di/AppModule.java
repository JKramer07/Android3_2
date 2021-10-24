package com.geek.android3_2.di;

import com.geek.android3_2.data.remote.HerokuApi;
import com.geek.android3_2.data.remote.RetrofitClient;
import com.geek.android3_2.data.repositories.HerokuRepositoryImpl;
import com.geek.android3_2.domain.repositories.HerokuRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static HerokuRepository provideRep(HerokuApi api){
        return new HerokuRepositoryImpl(api);
    }

    @Provides
    public static RetrofitClient provideClient(){
        return new RetrofitClient();
    }

    @Provides
    public static HerokuApi provideApi(RetrofitClient client){
        return client.provideHerokuApi();
    }
}
