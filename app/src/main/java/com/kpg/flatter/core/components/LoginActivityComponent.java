package com.kpg.flatter.core.components;

import com.kpg.flatter.LoginActivity;
import com.kpg.flatter.core.modules.EventBusModule;
import com.kpg.flatter.core.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Injects to LoginActivity
 */
@Singleton
@Component(modules = {EventBusModule.class, RetrofitModule.class})
public interface LoginActivityComponent {
    void inject(LoginActivity activity);
}
