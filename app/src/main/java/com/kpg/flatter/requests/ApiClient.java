package com.kpg.flatter.requests;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Retrofit retrofit = null;

    private String baseUrl;

    @Inject
    public ApiClient(@Named("baseUrl") String baseUrl){
        this.baseUrl = baseUrl;
    }

    /**
     * Retrofit singleton
     * @return get Retrofit client that handles all server requests
     */
    public Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;

    }
}
