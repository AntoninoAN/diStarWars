package com.example.daggerstarwars.di.module;

import com.example.daggerstarwars.adapter.RecyclerViewAdapter;
import com.example.daggerstarwars.di.qualifier.ActivityContext;
import com.example.daggerstarwars.di.scopes.ActivityScope;
import com.example.daggerstarwars.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityContextModule.class})
public class AdapterModule {
    @Provides
    @ActivityScope
    public RecyclerViewAdapter
        getStarWarsPeopleLIst(RecyclerViewAdapter.ClickListener clickListener){
        return new RecyclerViewAdapter(clickListener);
    }

    @Provides
    @ActivityScope
    public RecyclerViewAdapter.ClickListener getClickListener(MainActivity mainActivity){
        return mainActivity;
    }
}
