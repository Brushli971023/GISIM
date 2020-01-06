package com.bawei6.usermodule.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.baselibrary.base.BaseFragment;
import com.bawei6.baselibrary.basemvp.BasePresenter;
import com.bawei6.baselibrary.basemvp.IBaseModel;
import com.bawei6.baselibrary.basemvp.IBaseView;
import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.baselibrary.utils.UIToastUtils;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.contract.UserContract;
import com.bawei6.usermodule.model.UserModel;
import com.bawei6.usermodule.model.bean.UserInfoBean;
import com.bawei6.usermodule.presenter.UserPresenter;
import com.bawei6.usermodule.view.adapter.AddPersonListRecyclerAdapter;
import com.bawei6.usermodule.view.adapter.UserPersonsListRecyclerAdapter;
import com.gjiazhe.wavesidebar.WaveSideBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AZhung
 * @date 2020/1/2
 * @description
 */
public class UserFriendFragment extends BaseFragment implements UserContract.View {

    private UserPresenter userPresenter;
    private RecyclerView user_friends_recyclerView;
    private WaveSideBar friend_fragment_waveSideBar;
    private List<UserInfoBean> friends = new ArrayList<>();
    private UserPersonsListRecyclerAdapter addPersonListRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_friend_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        userPresenter.getFriends(BaseConstant.userCode);
    }

    private void initView(View view) {
        user_friends_recyclerView = view.findViewById(R.id.user_friends_recyclerView);
        friend_fragment_waveSideBar = view.findViewById(R.id.friend_fragment_waveSideBar);

    }

    @Override
    public void registerResult(String string) {

    }

    @Override
    public void loginResult(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchResult(List<UserInfoBean> userInfoBeanList) {
        friends.clear();
        friends.addAll(userInfoBeanList);
        user_friends_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addPersonListRecyclerAdapter = new UserPersonsListRecyclerAdapter(getContext(), friends);
        user_friends_recyclerView.setAdapter(addPersonListRecyclerAdapter);
    }

    @Override
    public IBaseModel createModel() {
        return new UserModel();
    }

    @Override
    public IBaseView createView() {
        return this;
    }

    @Override
    public BasePresenter createPresenter() {
        userPresenter = new UserPresenter();
        return userPresenter;
    }
}
