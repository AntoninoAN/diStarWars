package com.example.daggerstarwars.di.component;

import com.example.daggerstarwars.di.scopes.ActivityScope;
import com.example.daggerstarwars.ui.DetailActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface DetailActivityComponent {
    void injectDetailActivity(DetailActivity detailActivity);
}
