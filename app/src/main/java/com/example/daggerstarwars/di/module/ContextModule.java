package com.example.daggerstarwars.di.module;

import android.content.Context;

import com.example.daggerstarwars.di.qualifier.ApplicationContext;
import com.example.daggerstarwars.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext(){
        return context;
    }
}
