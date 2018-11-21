package com.kpg.flatter;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_textedit) EditText loginField;
    @BindView(R.id.password_textedit) EditText passwordField;
    @BindView(R.id.signup_textview) TextView signupText;
    @BindView(R.id.signin_button) Button signinButton;

    @OnClick(R.id.signin_button) void signIn(){
        /*
            Sign in implementation
        */
        System.out.println("Sign in button clicked");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        ButterKnife.bind(this);
    }
}
