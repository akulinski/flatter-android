package com.kpg.flatter.requests.callbacks;

import android.util.Log;

import com.google.common.eventbus.EventBus;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kpg.flatter.eventbus.events.SigninEvent;
import com.kpg.flatter.utills.Status;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Retrofit handling of sever callbacks
 */
public class SigninCallback implements Callback<JsonObject> {

   private EventBus eventBus;

   @Inject
    public SigninCallback(EventBus eventBus){
        this.eventBus = eventBus;
    }

    /**
     * Method that handle successful response form the server
     * EventBus sends event to the LoginActivity
     * @param call generated call to the server
     * @param response request body form server
     */
    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

        JsonObject jsonResponse = response.body();
        JsonElement status = jsonResponse.get("status");
        JsonElement token = jsonResponse.get("token");

        if (status.getAsString().equals("ACCEPTED")){
            eventBus.post(new SigninEvent(Status.SUCCES.str));
            Log.e("ACCEPTED","YES");
        } else {
            Log.e("ACCEPTED","NO");
            eventBus.post(new SigninEvent(Status.FALIURE.str));
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

        Log.d("ACCEPTED",t.getLocalizedMessage());
        Log.d("URL",call.request().url().toString());
        eventBus.post(new SigninEvent(Status.FALIURE.str));

    }

}
