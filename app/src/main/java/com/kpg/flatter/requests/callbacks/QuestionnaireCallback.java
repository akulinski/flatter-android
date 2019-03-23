package com.kpg.flatter.requests.callbacks;



import android.util.Log;

import com.google.common.eventbus.EventBus;
import com.kpg.flatter.eventbus.events.QuestionnaireEvent;
import com.kpg.flatter.utills.Status;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionnaireCallback implements Callback<ResponseBody> {

    private EventBus eventBus;
    private static final String UNAUTHORIZED = "Unauthorized";
    /**
     * @param eventBus injected event bus by dagger
     */
    @Inject
    public QuestionnaireCallback(EventBus eventBus) {
        this.eventBus = eventBus;
    }



    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        System.out.println(response.code());
        if (response.isSuccessful()) {
            eventBus.post(new QuestionnaireEvent(Status.SUCCES.str));
            Log.d("Status", "OK");
        } else if (response.code() == 401) {
            eventBus.post(new QuestionnaireEvent(UNAUTHORIZED));
            Log.d("STATUS", "Unauthorized");
        } else {
            eventBus.post(new QuestionnaireEvent(Status.FALIURE.str));
            Log.d("URL", call.request().url().toString());
            Log.d("STATUS", "Failure");
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.d("STATUS", t.getLocalizedMessage());
        Log.d("URL", call.request().url().toString());
        eventBus.post(new QuestionnaireEvent(Status.FALIURE.str));
    }
}
