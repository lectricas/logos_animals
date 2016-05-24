package com.pythonanywhere.polusov.logosanimals;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.pythonanywhere.polusov.logosanimals.di.DaggerNetComponent;
import com.pythonanywhere.polusov.logosanimals.di.NetComponent;
import com.pythonanywhere.polusov.logosanimals.di.NetModule;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.RealmConfiguration;
import lombok.Getter;
import okhttp3.OkHttpClient;


public class LogosApplication extends Application {

    @Getter
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        mNetComponent = DaggerNetComponent.builder()
                .netModule(new NetModule(getApplicationContext()))
                .build();


        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }
}
