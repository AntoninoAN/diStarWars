package com.example.daggerstarwars.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.daggerstarwars.MyApplication;
import com.example.daggerstarwars.R;
import com.example.daggerstarwars.adapter.RecyclerViewAdapter;
import com.example.daggerstarwars.di.component.ApplicationComponent;
import com.example.daggerstarwars.di.component.DaggerMainActivityComponent;
import com.example.daggerstarwars.di.component.MainActivityComponent;
import com.example.daggerstarwars.di.module.MainActivityContextModule;
import com.example.daggerstarwars.di.qualifier.ActivityContext;
import com.example.daggerstarwars.di.qualifier.ApplicationContext;
import com.example.daggerstarwars.pojo.StarWars;
import com.example.daggerstarwars.retrofit.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements RecyclerViewAdapter.ClickListener {

    private RecyclerView recyclerView;
    MainActivityComponent mainActivityComponent;

    @Inject
    public RecyclerViewAdapter recyclerViewAdapter;
    @Inject
    public ApiInterface apiInterface;
    @Inject
    @ApplicationContext
    public Context context;
    @Inject
    @ActivityContext
    public Context activityContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApplicationComponent applicationComponent =
                MyApplication.get(this).getApplicationComponent();

        mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityContextModule(new MainActivityContextModule(this))
                .applicationComponent(applicationComponent)
                .build();

        mainActivityComponent.injectMainActivity(this);

        recyclerView.setAdapter(recyclerViewAdapter);
        apiInterface.getPeople("json").enqueue(new Callback<StarWars>() {
            @Override
            public void onResponse(Call<StarWars> call, Response<StarWars> response) {
                populateRecyclerView(response.body().results);
            }

            @Override
            public void onFailure(Call<StarWars> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateRecyclerView(List<StarWars.People> results) {
        recyclerViewAdapter.setData(results);
    }

    @Override
    public void launchIntent(String filmName) {
        Toast.makeText(context, filmName, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(activityContext,
                DetailActivity.class).putExtra("url",filmName));
    }
}
