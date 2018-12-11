package com.kpg.flatter.requests.callbacks;

import android.util.Log;

import com.google.common.eventbus.EventBus;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kpg.flatter.eventbus.events.SignupEvent;
import com.kpg.flatter.utills.Status;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Retrofit handling of server callbacks
 */
public class SignupCallback implements Callback<JsonObject> {
    private EventBus eventBus;

    /**
     *
     * @param eventBus injected event bus by dagger
     */
    @Inject
    public SignupCallback(EventBus eventBus){
        this.eventBus = eventBus;
    }

    /**
     * Method that handle successful response form the server
     * EventBus sends event to the SignupActivity
     * @param call generated call to the server
     * @param response request body from server
     */
    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

        JsonElement status = response.body().get("status");

        if(status.getAsString().equals(Status.SUCCES.str)) {
            eventBus.post(new SignupEvent(Status.SUCCES.str));
            Log.d("STATUS","OK");
        } else  if (status.getAsString().equals(Status.LOGINEXISTS.str)){
            eventBus.post(new SignupEvent(Status.LOGINEXISTS.str));
            Log.d("STATUS","Login exists");
        } else if (status.getAsString().equals(Status.EMAILEXISTS.str)){
            eventBus.post(new SignupEvent(Status.EMAILEXISTS.str));
            Log.d("STATUS","Email exists");
        } else {
            eventBus.post(new SignupEvent(Status.FALIURE.str));
            Log.d("STATUS","Failure");
        }

    }

    /**
     * Method that handle failed response from the server
     * Printing exception and sending Event through EventBus
     * @param call generated call to the server
     * @param t error from the request
     */
    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {

        Log.d("STATUS",t.getLocalizedMessage());
        Log.d("URL",call.request().url().toString());
        eventBus.post(new SignupEvent(Status.FALIURE.str));

    }
}
