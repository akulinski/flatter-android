package com.kpg.flatter.requests.callbacks;

import com.google.common.eventbus.EventBus;
import com.google.gson.JsonObject;
import com.kpg.flatter.eventbus.events.PhotoAddEvent;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPhotoCallback implements Callback<JsonObject> {

    private EventBus eventBus;

    @Inject
    public AddPhotoCallback(EventBus eventBus){
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        if (response.isSuccessful()){
            eventBus.post(new PhotoAddEvent(true));
        }else {
            eventBus.post(new PhotoAddEvent(false));
        }
    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        eventBus.post(new PhotoAddEvent(false,t.getMessage()));
    }
}
