package com.example.daggerstarwars.di.component;

import android.content.Context;

import com.example.daggerstarwars.di.module.AdapterModule;
import com.example.daggerstarwars.di.qualifier.ActivityContext;
import com.example.daggerstarwars.di.scopes.ActivityScope;
import com.example.daggerstarwars.ui.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = AdapterModule.class,
        dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    @ActivityContext
    Context getContext();
    void injectMainActivity(MainActivity mainActivity);
}
