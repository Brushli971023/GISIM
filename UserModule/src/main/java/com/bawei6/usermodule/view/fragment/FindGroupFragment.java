package com.bawei6.usermodule.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bawei6.usermodule.R;
import com.bawei6.usermodule.view.adapter.MyContractPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AZhung
 * @date 2020/1/2
 * @description
 */
public class FindGroupFragment extends Fragment {
    private List<String> tabs = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private TabLayout find_group_tabLayout;
    private ViewPager find_group_pager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_group_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initData() {
        tabs.add("游戏");
        tabs.add("粉丝");
        tabs.add("交友");

        fragments.add(new FindGroupGameFragment());
        fragments.add(new FindGroupGameFragment());
        fragments.add(new FindGroupGameFragment());

        find_group_pager.setAdapter(new MyContractPagerAdapter(getChildFragmentManager(), fragments, tabs));
        find_group_tabLayout.setupWithViewPager(find_group_pager);
    }

    private void initView(View view) {
        find_group_tabLayout = view.findViewById(R.id.find_group_tabLayout);
        find_group_pager = view.findViewById(R.id.find_group_pager);
    }
}
