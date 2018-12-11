package com.kpg.flatter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kpg.flatter.R;
import com.kpg.flatter.adapters.ProfileViewPagerAdapter;
import com.kpg.flatter.fragments.profileconfiguration.ChooseType;
import com.kpg.flatter.fragments.profileconfiguration.Configured;
import com.kpg.flatter.fragments.profileconfiguration.SetUpPhoto;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ConfigureProfileActivity extends AppCompatActivity {

    @BindView(R.id.textTop) TextView topText;
    @BindView(R.id.profileViewPager) ViewPager profileViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_configuration_view);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        ButterKnife.bind(this);
        topText.setText("Configure profile");
        setupViewPager();

    }

    private void setupViewPager(){
        ProfileViewPagerAdapter profileViewPagerAdapter = new ProfileViewPagerAdapter(getSupportFragmentManager());
        profileViewPagerAdapter.addFragment(new SetUpPhoto(),"Setup Photo");
        profileViewPagerAdapter.addFragment(new ChooseType(),"Choose type");
        profileViewPagerAdapter.addFragment(new Configured(),"Configured");
        profileViewPager.setAdapter(profileViewPagerAdapter);
    }

    @OnClick(R.id.arrow) void nextFragemnt(){
        if (profileViewPager.getCurrentItem() == 2){
            Intent mainIntent = new Intent(getApplicationContext(), MainView.class);
            finish();
            startActivity(mainIntent);


        } else {
            profileViewPager.setCurrentItem(profileViewPager.getCurrentItem() + 1);
        }
    }

}
