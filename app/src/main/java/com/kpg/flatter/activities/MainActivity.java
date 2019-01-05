package com.kpg.flatter.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kpg.flatter.R;
import com.kpg.flatter.adapters.ViewPagerAdapter;
import com.kpg.flatter.fragments.mainview.ChatFragment;
import com.kpg.flatter.fragments.mainview.HomeFragment;
import com.kpg.flatter.fragments.mainview.ProfileFragment;
import com.kpg.flatter.fragments.mainview.SearchFragment;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainViewPager) ViewPager mainViewPager;
    @BindView(R.id.navbar) BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setContentView(R.layout.mainview);
        ButterKnife.bind(this);

        setupViewPager();
        setUpBottomNavigationBar();
        setUpScrollBottomNav();

    }

    private void setupViewPager(){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new HomeFragment(),"Home");
        viewPagerAdapter.addFragment(new SearchFragment(),"Search");
        viewPagerAdapter.addFragment(new ChatFragment(),"Chat");
        viewPagerAdapter.addFragment(new ProfileFragment(),"Profile");
        mainViewPager.setAdapter(viewPagerAdapter);

    }

    private void setUpBottomNavigationBar(){

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

    private void setUpScrollBottomNav(){
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

}
