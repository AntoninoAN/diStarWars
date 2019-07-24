package com.example.daggerstarwars.di.component;

import android.content.Context;

import com.example.daggerstarwars.MyApplication;
import com.example.daggerstarwars.di.module.ContextModule;
import com.example.daggerstarwars.di.module.RetrofitModule;
import com.example.daggerstarwars.di.qualifier.ApplicationContext;
import com.example.daggerstarwars.di.scopes.ApplicationScope;
import com.example.daggerstarwars.retrofit.ApiInterface;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@ApplicationScope
@Component(modules = {RetrofitModule.class, ContextModule.class})
public interface ApplicationComponent {
    public ApiInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    public void injectApplication(MyApplication application);
}
