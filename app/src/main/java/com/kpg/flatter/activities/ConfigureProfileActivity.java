package com.kpg.flatter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kpg.flatter.R;
import com.kpg.flatter.adapters.ViewPagerAdapter;
import com.kpg.flatter.fragments.profileconfiguration.ChooseTypeFragment;
import com.kpg.flatter.fragments.profileconfiguration.ConfiguredFragment;
import com.kpg.flatter.fragments.profileconfiguration.SetUpPhotoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfigureProfileActivity extends AppCompatActivity {

    @BindView(R.id.textTop) TextView topText;
    @BindView(R.id.profileViewPager) ViewPager profileViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.configureprofile);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        ButterKnife.bind(this);
        topText.setText("Configure profile");
        setupViewPager();

    }

    private void setupViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new SetUpPhotoFragment(),"Setup Photo");
        viewPagerAdapter.addFragment(new ChooseTypeFragment(),"Choose type");
        viewPagerAdapter.addFragment(new ConfiguredFragment(),"ConfiguredFragment");
        profileViewPager.setAdapter(viewPagerAdapter);
    }

    @OnClick(R.id.arrow) void nextFragemnt(){
        if (profileViewPager.getCurrentItem() == 2){
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            finish();
            startActivity(mainIntent);


        } else {
            profileViewPager.setCurrentItem(profileViewPager.getCurrentItem() + 1);
        }
    }

}
