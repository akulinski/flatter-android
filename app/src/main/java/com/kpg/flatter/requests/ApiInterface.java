package com.kpg.flatter.requests;

import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface that create schema of endpoints
 */
public interface ApiInterface {

    @POST("/users/signin")
    Call<JsonObject> signin(@Body HashMap<String, String> body);

    @GET("/users/getPhotos/{user}/{album}")
    Call<JsonObject> getPhotos(@Path("user")String user,@Path("album") String album);

    @POST("/users/addPhoto")
    Call<JsonObject> addPhoto(@Body String body);

    @POST("/users/signup")
    Call<JsonObject> signup(@Body HashMap<String, String> body);

}
