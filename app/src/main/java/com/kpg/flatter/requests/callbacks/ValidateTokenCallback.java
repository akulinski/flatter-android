package com.kpg.flatter.requests.callbacks;

import com.google.common.eventbus.EventBus;
import com.kpg.flatter.eventbus.events.ValidateTokenEvent;
import com.kpg.flatter.utills.Status;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class ValidateTokenCallback implements Callback<String> {

    private EventBus eventBus;

    @Inject
    public ValidateTokenCallback(EventBus eventBus) {
        this.eventBus = eventBus;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        String authority = response.body();

        if (response.isSuccessful()) {
            eventBus.post(new ValidateTokenEvent(Status.SUCCES.str));
        } else {
            eventBus.post(new ValidateTokenEvent(Status.FALIURE.str));
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        eventBus.post(new ValidateTokenEvent(Status.FALIURE.str));
    }
}
