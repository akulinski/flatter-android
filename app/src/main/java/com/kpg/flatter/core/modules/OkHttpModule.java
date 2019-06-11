package com.kpg.flatter.core.modules;

import com.kpg.flatter.requests.okhttp.OkHttpBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class OkHttpModule {

    @Provides
    @Singleton
    public OkHttpBuilder provideOkHttpBuilder() {
        return new OkHttpBuilder();
    }
}
