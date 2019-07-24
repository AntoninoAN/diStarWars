package com.example.daggerstarwars.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.daggerstarwars.MyApplication;
import com.example.daggerstarwars.R;
import com.example.daggerstarwars.di.component.ApplicationComponent;
import com.example.daggerstarwars.di.component.DaggerDetailActivityComponent;
import com.example.daggerstarwars.di.component.DetailActivityComponent;
import com.example.daggerstarwars.di.qualifier.ApplicationContext;
import com.example.daggerstarwars.pojo.Film;
import com.example.daggerstarwars.retrofit.ApiInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    DetailActivityComponent detailActivityComponent;

    @Inject
    public ApiInterface apiInterface;

    @Inject
    @ApplicationContext
    public Context context;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = findViewById(R.id.textView);

        String url = getIntent().getStringExtra("url");

        ApplicationComponent applicationComponent = MyApplication.get(this).getApplicationComponent();
        detailActivityComponent = DaggerDetailActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
        detailActivityComponent.injectDetailActivity(this);

        apiInterface.getFilmData(url, "json").enqueue(
                new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {
                        Film films = response.body();
                        String text = "Film name:\n" + films.title + "\nDirector:\n" + films.director;
                        textView.setText(text);
                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {

                    }
                }
        );
    }
}
