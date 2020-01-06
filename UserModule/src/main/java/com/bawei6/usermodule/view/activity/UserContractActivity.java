package com.bawei6.usermodule.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei6.baselibrary.base.BaseActivity;
import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.baselibrary.widget.CustomTitleBar;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.view.adapter.MyContractPagerAdapter;
import com.bawei6.usermodule.view.fragment.FindGroupGameFragment;
import com.bawei6.usermodule.view.fragment.UserFriendFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description 用户联系人
 */
@Route(path = BaseConstant.AROUTERTO_PHONECONTRACT)
public class UserContractActivity extends BaseActivity {

    private List<String> tabs = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private TabLayout user_contract_tabLayout;
    private ViewPager user_contract_pager;
    private CustomTitleBar user_contract_custom_bar;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contract);
        ARouter.getInstance().inject(this);
        initView();
        initData();
    }

    private void initData() {
        tabs.add("好友");
        tabs.add("分组");
        tabs.add("群聊");

        fragments.add(new UserFriendFragment());
        fragments.add(new FindGroupGameFragment());
        fragments.add(new FindGroupGameFragment());

        user_contract_pager.setAdapter(new MyContractPagerAdapter(getSupportFragmentManager(), fragments, tabs));
        user_contract_tabLayout.setupWithViewPager(user_contract_pager);
    }

    private void initView() {
        user_contract_tabLayout = (TabLayout) findViewById(R.id.user_contract_tabLayout);
        user_contract_pager = (ViewPager) findViewById(R.id.user_contract_pager);
        user_contract_custom_bar = (CustomTitleBar) findViewById(R.id.user_contract_custom_bar);

        user_contract_custom_bar.rightOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserContractActivity.this,AddPersonActivity.class));
            }
        });
        user_contract_custom_bar.leftOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
