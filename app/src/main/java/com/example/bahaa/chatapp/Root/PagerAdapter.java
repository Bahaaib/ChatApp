package com.example.bahaa.chatapp.Root;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    //Setting up the View Pager with tabs
    private int mTabsNum;

    public PagerAdapter(FragmentManager fm, int tabsNum) {
        super(fm);
        this.mTabsNum = tabsNum;
    }

    //Here we control the flow of the pager, What Fragment to go on clicking to which Tab..
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //TODO:Chat Fragment
            case 1:
                //TODO:Group Fragment
            case 2:
                //TODO: Contacts Fragment
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabsNum;
    }
}
