package com.example.daggerstarwars.retrofit;

import com.example.daggerstarwars.pojo.Film;
import com.example.daggerstarwars.pojo.StarWars;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET("people/?")
    Call<StarWars> getPeople(@Query("format") String format);

    @GET
    Call<Film> getFilmData(@Url String url,
                           @Query("format") String format);
}
