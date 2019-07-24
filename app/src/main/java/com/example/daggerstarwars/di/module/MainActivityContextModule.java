package com.example.daggerstarwars.di.module;

import android.content.Context;

import com.example.daggerstarwars.di.qualifier.ActivityContext;
import com.example.daggerstarwars.di.scopes.ActivityScope;
import com.example.daggerstarwars.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {
    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @ActivityScope
    public MainActivity providesMainActivity(){
        return mainActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context providesContext(){
        return context;
    }


}
