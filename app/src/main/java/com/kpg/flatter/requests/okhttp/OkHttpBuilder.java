package com.kpg.flatter.requests.okhttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public final class OkHttpBuilder {

    private OkHttpClient okHttpClient = null;

    public OkHttpBuilder() {

    }

    public OkHttpClient build(String token) {

        okHttpClient = new OkHttpClient().newBuilder().addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                    "Bearer " + token);
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }).build();

        return okHttpClient;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
