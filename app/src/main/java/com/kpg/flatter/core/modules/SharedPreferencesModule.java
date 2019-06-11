package com.kpg.flatter.core.modules;

import android.content.Context;

import com.kpg.flatter.core.SharedPreferencesWraper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    @Provides
    @Singleton
    public SharedPreferencesWraper provideSharedPreferences(Context context) {
        return new SharedPreferencesWraper(context);
    }
}
