package com.bawei6.usermodule.view.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author AZhung
 * @date 2020/1/2
 * @description
 */
public class MyContractPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> tabs;
    public MyContractPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments,List<String> tabs) {
        super(fm);
        this.fragments = fragments;
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
