package com.example.mystudy.tabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Nature on 2017/12/13.
 */

public class TabFragmentAdapter extends FragmentPagerAdapter {

    private List<TextFragment> fragments;
    private List<String> strings;

    public TabFragmentAdapter(List<TextFragment> fragments, List<String> strings, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragments = fragments;
        this.strings = strings;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return strings.size();
    }


}
