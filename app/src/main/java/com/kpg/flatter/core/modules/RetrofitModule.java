package com.kpg.flatter.core.modules;

import com.google.common.eventbus.EventBus;
import com.kpg.flatter.requests.ApiClient;
import com.kpg.flatter.requests.ApiInterface;
import com.kpg.flatter.requests.callbacks.SigninCallback;
import com.kpg.flatter.utills.Urls;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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

    /**
     *
     * @param eventBus provided by event bus module
     * @return callback for login screen
     */
    @Provides
    @Singleton
    public SigninCallback provideSigninCallback(EventBus eventBus){
        return new SigninCallback(eventBus);
    }
}
