package com.kpg.flatter.requests.callbacks;

import android.util.Log;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPhotosCallback implements Callback<JsonArray> {
    @Override
    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
        Log.d("GetPhotosSuccess", response.body().getAsJsonArray().toString());
    }

    @Override
    public void onFailure(Call<JsonArray> call, Throwable t) {
        Log.d("GetPhotosFail", t.getLocalizedMessage());
    }
}
