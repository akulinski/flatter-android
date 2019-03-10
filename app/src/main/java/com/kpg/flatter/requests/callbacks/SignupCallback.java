package com.kpg.flatter.requests.callbacks;

import android.util.Log;

import com.google.common.eventbus.EventBus;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kpg.flatter.eventbus.events.SignupEvent;
import com.kpg.flatter.utills.Status;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Retrofit handling of server callbacks
 */
public class SignupCallback implements Callback<ResponseBody> {
    private static final String LOGIN_NAME_ALREADY_USED = "Login name already used";
    private static final String EMAIL_IS_ALREADY_IN_USE = "Email is already in use";

    private EventBus eventBus;

    /**
     * @param eventBus injected event bus by dagger
     */
    @Inject
    public SignupCallback(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * Method that handle successful response form the server
     * EventBus sends event to the SignupActivity
     *
     * @param call     generated call to the server
     * @param response request body from server
     */
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

        if (response.isSuccessful()) {
            eventBus.post(new SignupEvent(Status.SUCCES.str));
            Log.d("STATUS", "OK");
        } else {
            String asString = response.body().toString();
            if (asString.contains(LOGIN_NAME_ALREADY_USED)) {
                eventBus.post(new SignupEvent(Status.LOGINEXISTS.str));
                Log.d("STATUS", "Login exists");
            } else if (asString.contains(EMAIL_IS_ALREADY_IN_USE)) {

                eventBus.post(new SignupEvent(Status.EMAILEXISTS.str));
                Log.d("STATUS", "Email exists");
            } else {
                eventBus.post(new SignupEvent(Status.FALIURE.str));
                Log.d("STATUS", "Failure");
            }
        }

    }

    /**
     * Method that handle failed response from the server
     * Printing exception and sending Event through EventBus
     *
     * @param call generated call to the server
     * @param t    error from the request
     */
    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

        Log.d("STATUS", t.getLocalizedMessage());
        Log.d("URL", call.request().url().toString());
        eventBus.post(new SignupEvent(Status.FALIURE.str));

    }
}
