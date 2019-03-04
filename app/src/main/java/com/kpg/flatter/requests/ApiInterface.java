package com.kpg.flatter.requests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kpg.flatter.requests.models.LoginPostModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface that create schema of endpoints
 */
public interface ApiInterface {

    @POST("/api/authenticate")
    Call<JsonObject> signin(@Body LoginPostModel loginPostModel);

    @GET("/api/photos")
    Call<JsonArray> getPhotos();

    @POST("/users/addPhoto")
    Call<JsonObject> addPhoto(@Body String body);

    @POST("/users/signup")
    Call<JsonObject> signup(@Body HashMap<String, String> body);

}
