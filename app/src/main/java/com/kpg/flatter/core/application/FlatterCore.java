package com.kpg.flatter.core.application;

import android.app.Application;

import com.kpg.flatter.core.components.DaggerLoginActivityComponent;
import com.kpg.flatter.core.components.LoginActivityComponent;
import com.kpg.flatter.core.modules.EventBusModule;
import com.kpg.flatter.core.modules.RetrofitModule;

/**
 * Core application class
 */
public class FlatterCore extends Application {

    /**
     * Built by dagger
     */
    private LoginActivityComponent loginActivityComponent;

    /**
     * Called when application starts - dagger builder call
     */
    @Override
    public void onCreate() {
        super.onCreate();
        loginActivityComponent = DaggerLoginActivityComponent.builder().eventBusModule(new EventBusModule()).retrofitModule(new RetrofitModule()).build();
    }

    public LoginActivityComponent getLoginActivityComponent() {
        return loginActivityComponent;
    }

    public void setLoginActivityComponent(LoginActivityComponent loginActivityComponent) {
        this.loginActivityComponent = loginActivityComponent;
    }
}
