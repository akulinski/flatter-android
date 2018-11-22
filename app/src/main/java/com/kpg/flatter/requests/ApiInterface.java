package com.kpg.flatter.requests;

import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Interface that create schema of endpoints
 */
public interface ApiInterface {

    @POST("/users/signin")
    Call<JsonObject> signin(@Body HashMap<String, String> body);

}
