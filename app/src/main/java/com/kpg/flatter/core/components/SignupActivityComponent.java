package com.kpg.flatter.core.components;


import com.kpg.flatter.activities.SignupActivity;
import com.kpg.flatter.core.modules.EventBusModule;
import com.kpg.flatter.core.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Injecting to sign up activity
 */
@Singleton
@Component(modules = {RetrofitModule.class, EventBusModule.class})
public interface SignupActivityComponent {
    void inject(SignupActivity activity);
}
