package com.kpg.flatter.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonObject;
import com.kpg.flatter.R;
import com.kpg.flatter.core.application.FlatterCore;
import com.kpg.flatter.dialogs.EmailBottomDialog;
import com.kpg.flatter.eventbus.events.SignupEvent;
import com.kpg.flatter.requests.ApiInterface;
import com.kpg.flatter.requests.callbacks.SignupCallback;
import com.kpg.flatter.utills.Status;
import com.kpg.flatter.utills.Validation;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.nameField) EditText name;
    @BindView(R.id.surnameField) EditText surname;
    @BindView(R.id.loginField) EditText login;
    @BindView(R.id.emailField) EditText email;
    @BindView(R.id.passwordField) EditText password;
    @BindView(R.id.passwordAgainField) EditText passwordAgain;

    @Inject EventBus eventBus;
    @Inject ApiInterface apiService;
    @Inject SignupCallback signupCallback;


    /**
     * Creating Sign up view, injecting dagger dependencies, subscribing to event bus
     * and binding butter knife
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        ((FlatterCore)getApplication()).getSignupActivityComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

        ButterKnife.bind(this);
        subscribeToEventBus();

    }

    /**
     * Validating all fields during sign up before sending the request to the server
     */
    @OnClick(R.id.bottomButton) void signUp(){

        if(name.getText().toString().equals("") || surname.getText().toString().equals("")
                || login.getText().toString().equals("") || email.getText().toString().equals("")
                || password.getText().toString().equals("") || password.getText().toString().equals("")){

            showDialog("Fill all fields");

        } else if(!password.getText().toString().equals(passwordAgain.getText().toString())) {

            showDialog("Passwords do not match");

        } else if(!Validation.validatePassword(password.getText().toString())){

            showDialog("Password too week. Must contains at least one digit, " +
                    "lower and upper case letter " +
                    "and special character and at least 6 characters");

        } else if(!Validation.validateEmail(email.getText().toString())){

            showDialog("Email is not valid");

        } else {

            Call<JsonObject> call = apiService.signup(createRequestBody());
            call.enqueue(signupCallback);

        }

    }

    /**
     * Creating request body
     * @return getting hash map for retrofit
     */
    private HashMap<String,String> createRequestBody(){

        HashMap<String,String> body = new HashMap<>();

        body.put("name",name.getText().toString());
        body.put("surname",surname.getText().toString());
        body.put("login",login.getText().toString());
        body.put("email",email.getText().toString());
        body.put("password",password.getText().toString());

        return body;

    }

    /**
     * subscribing to the Event Bus
     */
    private void subscribeToEventBus(){

        eventBus.register(new SignupEvenBusController());

    }

    /**
     * Sign up event bus controller for handling posted statuses
     */
    private class SignupEvenBusController{

        @Subscribe
        public void getStatus(SignupEvent event) {
            if (event.getStatus().equals(Status.SUCCES.str)) {

                EmailBottomDialog dialog = new EmailBottomDialog();
                dialog.show(getSupportFragmentManager(),"email sent");

            } else if(event.getStatus().equals(Status.EMAILEXISTS.str)){

                showDialog("Email exists");

            } else if(event.getStatus().equals(Status.LOGINEXISTS.str)){

                showDialog("Login exists");

            } else if(event.getStatus().equals(Status.FALIURE.str)){

                showDialog("Error occurred. Please try again.");
            }

        }

    }


    /**
     * Showing dialog
     * @param message displayed message on dialog box
     */
    private void showDialog(String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                }).show();

    }

}