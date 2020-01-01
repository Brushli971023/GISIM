package com.bawei6.usermodule.presenter;

import com.bawei6.baselibrary.basemvp.BasePresenter;
import com.bawei6.usermodule.contract.UserContract;
import com.bawei6.usermodule.model.UserModel;
import com.bawei6.usermodule.model.callback.UserCallBack;

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
                        getView().loginResult(string);
                    }
                }

                @Override
                public void onFaile(Throwable e) {
                    if (getView() != null){
                        getView().loginResult("登录失败"+":"+e.getMessage());
                    }
                }
            });
        }
    }
}
