package com.example.mystudy.tabLayout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.mystudy.R;

import java.util.ArrayList;
import java.util.List;


public class TabLayoutActivity extends AppCompatActivity {

    private SlidingTabLayout topTabLayout;
    private ViewPager viewPager;
    private List<String> strings = new ArrayList<>();
    private List<TextFragment> fragments = new ArrayList<>();
    private String[] tabTitles = {"Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        initFragments();
        initView();
    }

    private void initView() {
        topTabLayout = (SlidingTabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabFragmentAdapter(fragments, strings, getSupportFragmentManager()));
        topTabLayout.setupWithViewPager(viewPager);
        topTabLayout.setTabTextColors(getResources().getColor(R.color.radiobutton), getResources().getColor(R.color.radiobuttonzhong));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                topTabLayout.redrawIndicator(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (int i = 0; i < topTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = topTabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(1));
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag(i);
                }
            }
        }
    }

    private void initFragments() {
        for (String tabTitle : tabTitles) {
            TextFragment fragment = new TextFragment();
            fragment.setText(tabTitle);
            fragments.add(fragment);
            strings.add(tabTitle);
        }
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_tab_title);
        tv.setText(tabTitles[position]);
        return view;
    }
}
