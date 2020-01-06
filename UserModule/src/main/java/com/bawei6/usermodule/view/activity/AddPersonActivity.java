package com.bawei6.usermodule.view.activity;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bawei6.baselibrary.base.BaseActivity;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.view.adapter.MyContractPagerAdapter;
import com.bawei6.usermodule.view.fragment.FindGroupFragment;
import com.bawei6.usermodule.view.fragment.FindPresonFragment;
import com.bawei6.usermodule.view.fragment.UserFriendFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AddPersonActivity extends BaseActivity {

    private TabLayout addPerson_tabLayout;
    private ViewPager addPerson_viewPager;
    private List<String> tabs = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        initView();
        initData();
    }

    private void initView() {
        addPerson_tabLayout = (TabLayout) findViewById(R.id.addPerson_tabLayout);
        addPerson_viewPager = (ViewPager) findViewById(R.id.addPerson_viewPager);
    }

    private void initData() {
        tabs.add("找人");
        tabs.add("找群");

        fragments.add(new FindPresonFragment());
        fragments.add(new FindGroupFragment());

        addPerson_viewPager.setAdapter(new MyContractPagerAdapter(getSupportFragmentManager(),fragments,tabs));
        addPerson_tabLayout.setupWithViewPager(addPerson_viewPager);
    }
}
