package com.pythonanywhere.polusov.logosanimals.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmConfiguration;
import proxypref.ProxyPreferences;

@Module
public class NetModule {
    Context context;

    public NetModule(Context context){
       this.context = context;
    }

    @Provides
    @Singleton
    RealmConfiguration getRealmConfigureation(){
        return new RealmConfiguration.Builder(context).build();
    }


    @Provides
    @Singleton
    LogosPrefs providePrefs() {
        return ProxyPreferences
                .build(LogosPrefs.class, context.getSharedPreferences("preferences", 0));
    }

}
