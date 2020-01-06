package com.bawei6.usermodule.presenter;

import com.bawei6.baselibrary.basemvp.BasePresenter;
import com.bawei6.usermodule.contract.UserContract;
import com.bawei6.usermodule.model.UserModel;
import com.bawei6.usermodule.model.bean.UserInfoBean;
import com.bawei6.usermodule.model.callback.UserCallBack;

import java.util.List;

/**
 * @author AZhung
 * @date 2019/12/31
 * @description
 */
public class UserPresenter extends BasePresenter<UserModel,UserContract.View> implements UserContract.Presenter{


    @Override
    public void onViewDestroy() {
        if (model != null){
            model.stopRequest();
        }
    }

    @Override
    public void registerUser(String username, String password) {
        if (model != null){
            model.registerUserFromNet(username,password,new UserCallBack(){

                @Override
                public void onSuccess(String string) {
                    if (getView() != null){
                        getView().registerResult(string);
                    }
                }

                @Override
                public void onSuccess(List<UserInfoBean> userInfoBeanList) {

                }

                @Override
                public void onFaile(Throwable e) {
                    if (getView() != null){
                        getView().registerResult("注册失败"+":"+e.getMessage());
                    }
                }
            });
        }


    }

    @Override
    public void loginUser(String username, String password) {
        if (model != null){
            model.loginFromNet(username,password,new UserCallBack(){

                @Override
                public void onSuccess(String string) {
                    if (getView() != null){
                        if ("登录失败".equals(string)){
                            getView().registerResult(string);
                        }else {
                            getView().loginResult(string);
                        }

                    }
                }

                @Override
                public void onSuccess(List<UserInfoBean> userInfoBeanList) {

                }

                @Override
                public void onFaile(Throwable e) {
                    if (getView() != null){
                        getView().registerResult(e.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void searchFriend(String username, String nick) {
        if (model != null){
           model.searchFriend(username, nick, new UserCallBack() {
               @Override
               public void onSuccess(String string) {
                   getView().loginResult(string);
               }

               @Override
               public void onSuccess(List<UserInfoBean> userInfoBeanList) {
                    getView().searchResult(userInfoBeanList);
               }

               @Override
               public void onFaile(Throwable e) {
                   getView().loginResult(e.getMessage());
               }
           });
        }
    }

    @Override
    public void addFriend(String usercode, String friendcode, String username) {
        if (model != null){
            model.addFriend(usercode, friendcode, username, new UserCallBack() {
                @Override
                public void onSuccess(String string) {
                    getView().registerResult(string);
                }

                @Override
                public void onSuccess(List<UserInfoBean> userInfoBeanList) {

                }

                @Override
                public void onFaile(Throwable e) {
                    getView().registerResult(e.getMessage());
                }
            });
        }
    }

    @Override
    public void getFriends(String usercode) {
        if (model != null){
            model.getFriends(usercode, new UserCallBack() {
                @Override
                public void onSuccess(String string) {
                    getView().loginResult(string);
                }

                @Override
                public void onSuccess(List<UserInfoBean> userInfoBeanList) {
                    getView().searchResult(userInfoBeanList);
                }

                @Override
                public void onFaile(Throwable e) {
                    getView().loginResult(e.getMessage());
                }
            });
        }
    }
}
