package com.kpg.flatter.core.application;

import android.app.Application;

import com.kpg.flatter.core.components.DaggerLoginActivityComponent;
import com.kpg.flatter.core.components.DaggerMainActivityComponent;
import com.kpg.flatter.core.components.DaggerQuestionnaireActivityComponent;
import com.kpg.flatter.core.components.DaggerSignupActivityComponent;
import com.kpg.flatter.core.components.LoginActivityComponent;
import com.kpg.flatter.core.components.MainActivityComponent;
import com.kpg.flatter.core.components.QuestionnaireActivityComponent;
import com.kpg.flatter.core.components.SignupActivityComponent;
import com.kpg.flatter.core.modules.ContextModule;
import com.kpg.flatter.core.modules.EventBusModule;
import com.kpg.flatter.core.modules.RetrofitModule;
import com.kpg.flatter.core.modules.SharedPreferencesModule;

/**
 * Core application class
 */
public class FlatterCore extends Application {

    public static final EventBusModule EVENT_BUS_MODULE = new EventBusModule();
    public static final RetrofitModule RETROFIT_MODULE = new RetrofitModule();
    public static final SharedPreferencesModule SHARED_PREFERENCES_MODULE = new SharedPreferencesModule();
    /**
     * Built by dagger
     */
    private LoginActivityComponent loginActivityComponent;
    private SignupActivityComponent signupActivityComponent;
    private MainActivityComponent mainActivityComponent;
    private QuestionnaireActivityComponent questionnaireActivityComponent;

    /**
     * Called when application starts - dagger builder call
     */
    @Override
    public void onCreate() {
        super.onCreate();

        loginActivityComponent = DaggerLoginActivityComponent
                .builder()
                .eventBusModule(EVENT_BUS_MODULE)
                .retrofitModule(RETROFIT_MODULE)
                .contextModule(new ContextModule(getApplicationContext()))
                .sharedPreferencesModule(SHARED_PREFERENCES_MODULE)
                .build();


        signupActivityComponent = DaggerSignupActivityComponent
                .builder()
                .eventBusModule(EVENT_BUS_MODULE)
                .retrofitModule(RETROFIT_MODULE)
                .build();

        mainActivityComponent = DaggerMainActivityComponent
                .builder()
                .eventBusModule(EVENT_BUS_MODULE)
                .retrofitModule(RETROFIT_MODULE)
                .contextModule(new ContextModule(getApplicationContext()))
                .sharedPreferencesModule(SHARED_PREFERENCES_MODULE)
                .build();

        questionnaireActivityComponent = DaggerQuestionnaireActivityComponent
                .builder()
                .eventBusModule(EVENT_BUS_MODULE)
                .retrofitModule(RETROFIT_MODULE)
                .contextModule(new ContextModule(getApplicationContext()))
                .sharedPreferencesModule(SHARED_PREFERENCES_MODULE)
                .build();
    }

    public LoginActivityComponent getLoginActivityComponent() {
        return loginActivityComponent;
    }

    public void setLoginActivityComponent(LoginActivityComponent loginActivityComponent) {
        this.loginActivityComponent = loginActivityComponent;
    }

    public SignupActivityComponent getSignupActivityComponent() {
        return signupActivityComponent;
    }

    public void setSignupActivityComponent(SignupActivityComponent signupActivityComponent) {
        this.signupActivityComponent = signupActivityComponent;
    }

    public MainActivityComponent getMainActivityComponent() {
        return mainActivityComponent;
    }

    public void setMainActivityComponent(MainActivityComponent mainActivityComponent) {
        this.mainActivityComponent = mainActivityComponent;
    }

    public QuestionnaireActivityComponent getQuestionnaireActivityComponent() {
        return questionnaireActivityComponent;
    }

    public void setQuestionnaireActivityComponent(QuestionnaireActivityComponent questionnaireActivityComponent) {
        this.questionnaireActivityComponent = questionnaireActivityComponent;
    }
}
