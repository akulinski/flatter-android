package com.kpg.flatter.core.components;

import com.kpg.flatter.activities.LoginActivity;
import com.kpg.flatter.core.modules.ContextModule;
import com.kpg.flatter.core.modules.EventBusModule;
import com.kpg.flatter.core.modules.RetrofitModule;
import com.kpg.flatter.core.modules.SharedPreferencesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Injects to LoginActivity
 */
@Singleton
@Component(modules = {EventBusModule.class, RetrofitModule.class, ContextModule.class, SharedPreferencesModule.class})
public interface LoginActivityComponent {
    void inject(LoginActivity activity);
}
