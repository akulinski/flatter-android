package com.kpg.flatter.core.application;

import android.app.Application;

import com.kpg.flatter.activities.SignupActivity;
import com.kpg.flatter.core.components.DaggerLoginActivityComponent;
import com.kpg.flatter.core.components.DaggerSignupActivityComponent;
import com.kpg.flatter.core.components.LoginActivityComponent;
import com.kpg.flatter.core.components.SignupActivityComponent;
import com.kpg.flatter.core.modules.ContextModule;
import com.kpg.flatter.core.modules.EventBusModule;
import com.kpg.flatter.core.modules.RetrofitModule;
import com.kpg.flatter.core.modules.SharedPreferencesModule;

/**
 * Core application class
 */
public class FlatterCore extends Application {

    /**
     * Built by dagger
     */
    private LoginActivityComponent loginActivityComponent;
    private SignupActivityComponent signupActivityComponent;

     /**
     * Called when application starts - dagger builder call
     */
    @Override
    public void onCreate() {
        super.onCreate();
        loginActivityComponent = DaggerLoginActivityComponent
                .builder()
                .eventBusModule(new EventBusModule())
                .retrofitModule(new RetrofitModule())
                .contextModule(new ContextModule(getApplicationContext()))
                .sharedPreferencesModule(new SharedPreferencesModule())
                .build();

        signupActivityComponent = DaggerSignupActivityComponent
                .builder()
                .eventBusModule(new EventBusModule())
                .retrofitModule(new RetrofitModule())
                .build();

    }

    public LoginActivityComponent getLoginActivityComponent() {
        return loginActivityComponent;
    }

    public void setLoginActivityComponent(LoginActivityComponent loginActivityComponent) {
        this.loginActivityComponent = loginActivityComponent;
    }

    public SignupActivityComponent getSignupActivityComponent(){
        return signupActivityComponent;
    }

    public void setSignupActivityComponent(SignupActivityComponent signupActivityComponent){
        this.signupActivityComponent = signupActivityComponent;
    }
}
