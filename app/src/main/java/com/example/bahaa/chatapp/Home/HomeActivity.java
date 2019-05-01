package com.example.bahaa.chatapp.Home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.bahaa.chatapp.R;
import com.example.bahaa.chatapp.Root.PagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    //Butterknife
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //inject Views via Butterknife..
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        //Adding Three tabs to the screen
        tabLayout.addTab(tabLayout.newTab().setText(R.string.chat_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.group_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.contacts_tab));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //Setting up the View Pager that allows flipping activity fragments horizontally like a page
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Free up memory from views
        unbinder.unbind();
    }
}
