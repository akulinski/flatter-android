package com.kpg.flatter.core.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module providing application (context)
 */

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }


    @Provides
    @Singleton
    public Application providesApplication() {
        return mApplication;
    }
}