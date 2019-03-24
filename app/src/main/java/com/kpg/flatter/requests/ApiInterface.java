package com.kpg.flatter.requests;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kpg.flatter.requests.models.LoginPostModel;
import com.kpg.flatter.requests.models.QuestionnairePostModel;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
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

    @GET("/api/authenticate")
    Call<String> validateToken();

    @GET("/api/photos")
    Call<JsonArray> getPhotos();

    @POST("/users/addPhoto")
    Call<JsonObject> addPhoto(@Body String body);

    @POST("api/register")
    Call<ResponseBody> signup(@Body HashMap<String, String> body);

    @POST("/api/questionnaires")
    Call<ResponseBody> createQuestionnaire(@Body QuestionnairePostModel body);

}
