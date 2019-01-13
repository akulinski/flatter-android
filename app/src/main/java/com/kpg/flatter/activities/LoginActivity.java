package com.kpg.flatter.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.kpg.flatter.R;
import com.kpg.flatter.core.SharedPreferencesWraper;
import com.kpg.flatter.core.application.FlatterCore;
import com.kpg.flatter.core.exceptions.SharedPreferenceValueNotFoundException;
import com.kpg.flatter.eventbus.events.SigninEvent;
import com.kpg.flatter.requests.ApiInterface;
import com.kpg.flatter.requests.callbacks.SigninCallback;
import com.kpg.flatter.utills.Status;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Credentials;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_textedit)
    EditText loginField;
    @BindView(R.id.password_textedit)
    EditText passwordField;
    @BindView(R.id.signup_textview)
    TextView signupText;
    @BindView(R.id.signin_button)
    Button signinButton;
    @BindView(R.id.textTop)
    TextView topText;


    @Inject
    EventBus eventBus;
    @Inject
    ApiInterface apiService;
    @Inject
    SigninCallback signinCallback;

    private SharedPreferencesWraper sharedPreferencesWraper;

    /**
     * Creates view form xml, connects to server, bind ButterKnife
     * and subscribe to EventBus
     */
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ((FlatterCore) getApplication()).getLoginActivityComponent().inject(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.signin);
        ButterKnife.bind(this);
        topText.setText(R.string.app_name);
        subscribeToEventBus();
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            String credentials = sharedPreferencesWraper.readStringFromPreferences(getString(R.string.credentials));
            Call<JsonObject> call = apiService.signin(credentials);
            call.enqueue(signinCallback);
        } catch (SharedPreferenceValueNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * On click action for sign in button
     * It creates request to a server to authenticate login data
     */
    @OnClick(R.id.signin_button)
    void signIn() {

        // !!
        // !!
        // TEST INTENT
        // TO BO DELETED
        // !!
        // !!
        if (loginField.getText().toString().equals("test")) {
            Intent mainViewIntent = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(mainViewIntent);
            return;
        }

        if (loginField.getText().toString().equals("")
                || passwordField.getText().toString().equals("")) {

            showDialog("Fill all fields");

        } else {

            String credentials = Credentials.basic(loginField.getText().toString(), passwordField.getText().toString());

            sharedPreferencesWraper.addStringToPreferences(getString(R.string.credentials), credentials);

            Call<JsonObject> call = apiService.signin(credentials);
            call.enqueue(signinCallback);

            loginField.getText().clear();
            passwordField.getText().clear();
        }

    }

    /**
     * On click action for sign up general_toptext
     */
    @OnClick(R.id.signup_textview)
    void signUp() {
        Intent signupIntent = new Intent(this, SignupActivity.class);
        startActivity(signupIntent);
    }

    /**
     * Registering LoginActivity class to the EventBus
     */
    private void subscribeToEventBus() {
        eventBus.register(new SigninEventBus());
    }

    /**
     * Class that handles arrived event through EventBus
     */
    private final class SigninEventBus {

        @Subscribe
        public void getStatus(SigninEvent event) {

            if (event.getStatus().equals(Status.SUCCES.str)) {

                //Intent configureProfileIntent = new Intent(getApplicationContext(),
                //        ConfigureProfileActivity.class);
                Intent mainViewIntent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(mainViewIntent);
            } else showDialog("Login or password is incorrect");

            if (event.getStatus().equals(Status.SUCCES.str)) {
                showDialog("Signed in");
            } else {
                showDialog("Not signed in");
                sharedPreferencesWraper.removeStringCredentials(getString(R.string.credentials));
            }
            loginField.getText().clear();
            passwordField.getText().clear();

        }

    }

    /**
     * Creates Dialog with an OK button
     *
     * @param message message displayed on Dialog
     */
    private void showDialog(String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();

    }

    /**
     * Get the request HashMap
     *
     * @return created HashMap that will be generated as json through server request
     */
    private HashMap<String, String> createSigninRequestBody() {

        HashMap<String, String> body = new HashMap<>();

        body.put("login", loginField.getText().toString());
        body.put("password", passwordField.getText().toString());

        return body;

    }

    @Inject
    public void setSharedPreferencesWraper(SharedPreferencesWraper sharedPreferencesWraper) {
        this.sharedPreferencesWraper = sharedPreferencesWraper;
    }

}
