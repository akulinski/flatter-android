package com.kpg.flatter.activities;

import android.annotation.SuppressLint;
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
import com.kpg.flatter.eventbus.events.ValidateTokenEvent;
import com.kpg.flatter.requests.ApiInterface;
import com.kpg.flatter.requests.callbacks.SigninCallback;
import com.kpg.flatter.requests.callbacks.ValidateTokenCallback;
import com.kpg.flatter.requests.models.LoginPostModel;
import com.kpg.flatter.utills.Status;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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


    private EventBus eventBus;

    private ApiInterface apiService;

    private ApiInterface apiInterfaceWithToken;

    private SigninCallback signinCallback;


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
            String readTokenFromPreferences = sharedPreferencesWraper.readStringFromPreferences(getString(R.string.token_resource));

            if(!readTokenFromPreferences.equals("")){
                checkToken();
            }

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

        if (debugLogin()) return;

        validateFieldsAndCreateRequest();

    }

    private void validateFieldsAndCreateRequest() {
        if (isLoginOrPasswordEmpty()) {
            showDialog("Fill all fields");

        } else {
            createRequestFromFields();
            clearFields();
        }
    }

    private void clearFields() {
        loginField.getText().clear();
        passwordField.getText().clear();
    }

    private void checkToken(){
        ValidateTokenCallback validateTokenCallback = new ValidateTokenCallback(eventBus);
        Call<String> call = apiService.validateToken();
        call.enqueue(validateTokenCallback);
    }

    private void createRequestFromFields() {
        LoginPostModel loginPostModel = new LoginPostModel(passwordField.getText().toString(), loginField.getText().toString());

        Call<JsonObject> call = apiService.signin(loginPostModel);
        call.enqueue(signinCallback);
    }

    private boolean isLoginOrPasswordEmpty() {
        return loginField.getText().toString().equals("") || passwordField.getText().toString().equals("");
    }

    /**
     * TODO remove after testing phase
     *
     * @return
     */
    private boolean debugLogin() {
        if (loginField.getText().toString().equals("test")) {
            Intent mainViewIntent = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(mainViewIntent);
            return true;
        }
        return false;
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
        eventBus.register(new SigninEventListener());
        eventBus.register(new ValidateTokenEventListener());
    }

    /**
     * Class that handles arrived event through EventBus
     */
    private final class SigninEventListener {

        @Subscribe
        public void getStatus(SigninEvent event) {

            if (event.getStatus().equals(Status.SUCCES.str)) {
                saveTokenAndStartMainActivity(event);
            } else showDialog("Login or password is incorrect");

            clearFields();

        }

        private void saveTokenAndStartMainActivity(SigninEvent event) {
            sharedPreferencesWraper.addStringToPreferences(getString(R.string.token_resource), event.getToken());
            Intent mainViewIntent = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(mainViewIntent);
        }

    }

    private final class ValidateTokenEventListener{

        @Subscribe
        public void valideToken(ValidateTokenEvent validateTokenEvent){
            if(validateTokenEvent.getStatus().equals(Status.SUCCES.str)){
                Intent mainViewIntent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(mainViewIntent);
            }
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
                .setPositiveButton("OK", (dialogInterface, i) -> {
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

    @Inject
    @Named("apiWithToken")
    public void setApiInterfaceWithToken(ApiInterface apiInterfaceWithToken) {
        this.apiInterfaceWithToken = apiInterfaceWithToken;
    }


    @Inject
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Inject
    public void setApiService(ApiInterface apiService) {
        this.apiService = apiService;
    }

    @Inject
    public void setSigninCallback(SigninCallback signinCallback) {
        this.signinCallback = signinCallback;
    }

}
