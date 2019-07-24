package com.example.daggerstarwars.pojo;

import com.google.gson.annotations.SerializedName;

public class Film {
    @SerializedName("title")
    public String title;
    @SerializedName("director")
    public String director;
}
