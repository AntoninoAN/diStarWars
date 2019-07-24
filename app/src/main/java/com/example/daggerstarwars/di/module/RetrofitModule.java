package com.example.daggerstarwars.di.module;

import com.example.daggerstarwars.di.scopes.ApplicationScope;
import com.example.daggerstarwars.retrofit.ApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Provides
    @ApplicationScope
    ApiInterface getApiInterface(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @ApplicationScope
    Retrofit getRetrofit(){
        return new
                Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
