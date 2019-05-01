package com.example.bahaa.chatapp.Root;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.bahaa.chatapp.Home.ChatsFragment;
import com.example.bahaa.chatapp.Home.ContactsFragment;
import com.example.bahaa.chatapp.Home.GroupsFragment;

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
                return new ChatsFragment();
            case 1:
                return new GroupsFragment();
            case 2:
                return new ContactsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabsNum;
    }
}
