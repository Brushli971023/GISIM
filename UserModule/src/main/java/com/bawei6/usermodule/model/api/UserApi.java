package com.bawei6.usermodule.model.api;

import com.bawei6.baselibrary.data.BaseBean;
import com.bawei6.usermodule.model.bean.UserInfoBean;
import com.bawei6.usermodule.model.bean.UserLoginBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author 李荘
 * @time 2019-12-05
 * @desc 用户中心API
 */
public interface UserApi {

    /**
     * @desc 获取验证码
     * @param phoneNumber
     * @return
     */
    @GET("api/User/getAuthCode?")
    Observable<BaseBean<String>> getCode(@Query("phoneNumber") String phoneNumber);

    /**
     * @desc 注册用户
     * @param userInfoBean
     * @return
     */
    @POST("api/User/register")
    Observable<BaseBean<UserInfoBean>> getRegister(@Body UserInfoBean userInfoBean);

    /**
     * @desc 用户登录
     * @param userLoginBean
     * @return
     */
    @POST("api/User/login")
    Observable<BaseBean<UserLoginBean>> getLogin(@Body UserLoginBean userLoginBean);

    /**
     * @desc 用户登录
     * @param userLoginBean
     * @return
     */
    @POST("api/User/modifyPwd")
    Observable<BaseBean<String>> getChangePassword(@Body UserLoginBean userLoginBean);
}

