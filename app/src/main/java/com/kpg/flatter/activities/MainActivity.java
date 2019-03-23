package com.kpg.flatter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kpg.flatter.R;
import com.kpg.flatter.adapters.ViewPagerAdapter;
import com.kpg.flatter.core.SharedPreferencesWraper;
import com.kpg.flatter.core.application.FlatterCore;
import com.kpg.flatter.core.exceptions.SharedPreferenceValueNotFoundException;
import com.kpg.flatter.fragments.mainview.ChatFragment;
import com.kpg.flatter.fragments.mainview.HomeFragment;
import com.kpg.flatter.fragments.mainview.ProfileFragment;
import com.kpg.flatter.fragments.mainview.SearchFragment;
import com.kpg.flatter.requests.ApiClient;
import com.kpg.flatter.requests.ApiInterface;
import com.kpg.flatter.requests.callbacks.GetPhotosCallback;
import com.kpg.flatter.requests.callbacks.QuestionnaireCallback;
import com.kpg.flatter.requests.models.QuestionnairePostModel;
import com.kpg.flatter.utills.Urls;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainViewPager)
    ViewPager mainViewPager;
    @BindView(R.id.navbar)
    BottomNavigationView bottomNavigationView;

    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ((FlatterCore) getApplication()).getMainActivityComponent().inject(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setContentView(R.layout.mainview);
        ButterKnife.bind(this);

        setupViewPager();
        setUpBottomNavigationBar();
        setUpScrollBottomNav();

        Intent i = new Intent(getApplicationContext(), QuestionnaireActivity.class);
        startActivity(i);

    }


    private void setupViewPager() {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new HomeFragment(), "Home");
        viewPagerAdapter.addFragment(new SearchFragment(), "Search");
        viewPagerAdapter.addFragment(new ChatFragment(), "Chat");
        viewPagerAdapter.addFragment(new ProfileFragment(), "Profile");
        mainViewPager.setAdapter(viewPagerAdapter);

    }

    private void setUpBottomNavigationBar() {

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.ic_home:
                    mainViewPager.setCurrentItem(0);
                    break;
                case R.id.ic_search:
                    mainViewPager.setCurrentItem(1);
                    break;
                case R.id.ic_chat:
                    mainViewPager.setCurrentItem(2);
                    break;
                case R.id.ic_profile:
                    mainViewPager.setCurrentItem(3);
                    break;
            }
            return false;
        });

    }

    private void setUpScrollBottomNav() {
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Inject
    @Named("apiWithToken")
    public void setApiService(ApiInterface apiService) {
        this.apiService = apiService;
    }
}
