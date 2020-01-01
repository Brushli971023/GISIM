package com.bawei6.usermodule.contract;

import com.bawei6.baselibrary.basemvp.IBaseModel;
import com.bawei6.baselibrary.basemvp.IBaseView;
import com.bawei6.usermodule.model.callback.UserCallBack;

/**
 * @author AZhung
 * @date 2019/12/31
 * @description User模块契约类
 */
public interface UserContract {
    interface Model extends IBaseModel {
        /**
         *  发送两次请求分别注册webserver服务器和openfire服务器
         *
         * @param username 用户名
         * @param password 密码
         * @param callBack 接口回调
         */
        void registerUserFromNet(String username, String password, UserCallBack callBack);

        /**
         *
         * 发送两次请求分别登录webserver服务器和openfire服务器
         *
         * @param username 用户名
         * @param password 密码
         * @param callBack 接口回调
         */
        void loginFromNet(String username, String password, UserCallBack callBack);
    }

    interface View extends IBaseView {
        /**
         * 注册结果
         * @param string 返回结果
         */
        void registerResult(String string);

        /**
         * 登录结果
         * @param string 返回结果
         */
        void loginResult(String string);
    }

    interface Presenter {
        /**
         * p层调用m层网络注册方法
         * @param username 用户名
         * @param password 密码
         */
        void registerUser(String username,String password);
        /**
         * p层调用m层网络登录方法
         * @param username 用户名
         * @param password 密码
         */
        void loginUser(String username,String password);
    }

}
