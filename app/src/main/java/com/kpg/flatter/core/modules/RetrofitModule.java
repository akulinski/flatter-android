package com.kpg.flatter.core.modules;

import android.content.Context;
import android.util.Log;

import com.google.common.eventbus.EventBus;
import com.kpg.flatter.R;
import com.kpg.flatter.core.SharedPreferencesWraper;
import com.kpg.flatter.core.exceptions.SharedPreferenceValueNotFoundException;
import com.kpg.flatter.requests.ApiClient;
import com.kpg.flatter.requests.ApiInterface;
import com.kpg.flatter.requests.callbacks.AddPhotoCallback;
import com.kpg.flatter.requests.callbacks.QuestionnaireCallback;
import com.kpg.flatter.requests.callbacks.SigninCallback;
import com.kpg.flatter.requests.callbacks.SignupCallback;
import com.kpg.flatter.requests.okhttp.OkHttpBuilder;
import com.kpg.flatter.utills.Urls;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module that provides retrofit obj. and
 * related objects.
 */

@Module
public final class RetrofitModule {

    /**
     *
     * @return base url of server
     */
    @Provides
    @Singleton
    @Named("baseUrl")
    public String provideBaseUrl(){
        return Urls.SERVER.url;
    }

    /**
     *
     * @param { @link #provideBaseUrl() }
     * @return instance of api client with base URL
     */
    @Provides
    @Singleton
    public ApiInterface provideApiInterface(@Named("baseUrl") String baseUrl){
        return new ApiClient(baseUrl).getClient().create(ApiInterface.class);
    }


    @Provides
    @Singleton
    @Named("apiWithToken")
    public ApiInterface provideRetrofit(@Named("baseUrl") String baseUrl,SharedPreferencesWraper sharedPreferencesWraper) {
        try {
            Log.e("Token",sharedPreferencesWraper.readStringFromPreferences("TOKEN"));
            return new ApiClient(Urls.SERVER.url)
                    .getClientWithInterceptor(sharedPreferencesWraper.readStringFromPreferences("TOKEN"))
                    .create(ApiInterface.class);
        } catch (SharedPreferenceValueNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param eventBus provided by event bus module
     * @return callback for login screen
     */
    @Provides
    @Singleton
    public SigninCallback provideSigninCallback(EventBus eventBus, SharedPreferencesWraper sharedPreferencesWraper){
        return new SigninCallback(eventBus,sharedPreferencesWraper);
    }

    @Provides
    @Singleton
    public AddPhotoCallback provideAddPhotoCallback(EventBus eventBus) {

        return new AddPhotoCallback(eventBus);

    }

    /**
     *
     * @param eventBus provided by event bus module
     * @return callback for sign up screen
     */
    @Provides
    @Singleton
    public SignupCallback provideSignupCallback(EventBus eventBus){

        return new SignupCallback(eventBus);

    }

    @Provides
    @Singleton
    public QuestionnaireCallback provideQuestionnaireCallback(EventBus eventBus) {
        return new QuestionnaireCallback(eventBus);
    }
}
