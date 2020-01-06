package com.bawei6.usermodule.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.baselibrary.base.BaseFragment;
import com.bawei6.baselibrary.base.BaseMvpActivity;
import com.bawei6.baselibrary.base.BaseMvpFragment;
import com.bawei6.baselibrary.basemvp.BasePresenter;
import com.bawei6.baselibrary.basemvp.IBaseModel;
import com.bawei6.baselibrary.basemvp.IBaseView;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.contract.UserContract;
import com.bawei6.usermodule.model.UserModel;
import com.bawei6.usermodule.model.bean.UserInfoBean;
import com.bawei6.usermodule.presenter.UserPresenter;
import com.bawei6.usermodule.view.activity.PhoneContactActivity;
import com.bawei6.usermodule.view.adapter.AddPersonListRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AZhung
 * @date 2020/1/2
 * @description
 */
public class FindPresonFragment extends BaseFragment implements UserContract.View{

    private LinearLayout add_phone_contract;
    private androidx.appcompat.widget.SearchView find_person_searchView;
    private RecyclerView finded_person_recyclerView;
    private List<UserInfoBean> friends = new ArrayList<>();
    private AddPersonListRecyclerAdapter addPersonListRecyclerAdapter;
    private UserPresenter userPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_person_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
    }

    private void initEvent() {
        add_phone_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PhoneContactActivity.class));
            }
        });

        find_person_searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                userPresenter.searchFriend(query,"");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initView(View view) {
        add_phone_contract = view.findViewById(R.id.add_phone_contract);
        find_person_searchView = view.findViewById(R.id.find_person_searchView);
        finded_person_recyclerView = view.findViewById(R.id.finded_person_recyclerView);

        finded_person_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addPersonListRecyclerAdapter = new AddPersonListRecyclerAdapter(getContext(), friends,userPresenter);
        finded_person_recyclerView.setAdapter(addPersonListRecyclerAdapter);
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

    @Override
    public void registerResult(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginResult(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchResult(List<UserInfoBean> userInfoBeanList) {
        friends.clear();
        friends.addAll(userInfoBeanList);
        addPersonListRecyclerAdapter.notifyDataSetChanged();
    }
}
