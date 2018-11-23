package com.kpg.flatter.requests;

import com.kpg.flatter.utills.Urls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    /**
     * Retrofit singleton
     * @return get Retrofit client that handles all server requests
     */
    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.SERVER.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;

    }
}
