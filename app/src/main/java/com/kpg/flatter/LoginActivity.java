package com.kpg.flatter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.gson.JsonObject;
import com.kpg.flatter.core.application.FlatterCore;
import com.kpg.flatter.eventbus.events.SigninEvent;
import com.kpg.flatter.requests.ApiInterface;
import com.kpg.flatter.requests.callbacks.SigninCallback;
import com.kpg.flatter.utills.Status;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_textedit) EditText loginField;
    @BindView(R.id.password_textedit) EditText passwordField;
    @BindView(R.id.signup_textview) TextView signupText;
    @BindView(R.id.signin_button) Button signinButton;


    @Inject EventBus eventBus;
    @Inject ApiInterface apiService;
    @Inject SigninCallback signinCallback;

    /**
     * Creates view form xml, connects to server, bind ButterKnife
     * and subscribe to EventBus
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ((FlatterCore)getApplication()).getLoginActivityComponent().inject(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.login_view);

        ButterKnife.bind(this);
        subscribeToEventBus();

    }

    /**
     * On click action for sign in button
     * It creates request to a server to authenticate login data
     */
    @OnClick(R.id.signin_button) void signIn(){

        Call<JsonObject> call = apiService.signin(createSigninRequestBody());
        call.enqueue(signinCallback);

        loginField.getText().clear();
        passwordField.getText().clear();

    }

    /**
     * Registering LoginActivity class to the EventBus
     */
    private void subscribeToEventBus(){
        eventBus.register(new SigninEventBus());
    }

    /**
     * Class that handles arrived event through EventBus
     */
    private final class SigninEventBus{

        @Subscribe
        public void getStatus(SigninEvent event) {

            if(event.getStatus().equals(Status.SUCCES.str)){
                showDialog("Signed in");
            } else showDialog("Not signed in");

        }

    }

    /**
     * Creates Dialog with an OK button
     * @param message message displayed on Dialog
     */
    private void showDialog(String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                }).show();

    }

    /**
     * Get the request HashMap
     * @return created HashMap that will be generated as json through server request
     */
    private HashMap<String,String> createSigninRequestBody(){

        HashMap<String, String> body = new HashMap<>();

        body.put("login",loginField.getText().toString());
        body.put("password",passwordField.getText().toString());

        return body;

    }

}
