package com.bawei6.usermodule.model.api;

import com.bawei6.baselibrary.data.BaseBean;
import com.bawei6.usermodule.model.bean.UserInfoBean;
import com.bawei6.usermodule.model.bean.UserLoginBean;

import java.util.List;

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
    Observable<BaseBean<UserInfoBean>> getLogin(@Body UserLoginBean userLoginBean);

    @POST("api/User/logout")
    Observable<BaseBean<String>> logout(@Body String username);

    @POST("api/User/modifyPwd")
    Observable<BaseBean<String>> modifyPwd(@Body BaseBean userEntity);

    @GET("api/Friend/getFriends")
    Observable<BaseBean<List<UserInfoBean>>> getFriends(@Query("usercode") String usercode);

    @GET("api/Friend/searchFriend")
    Observable<BaseBean<List<UserInfoBean>>> searchFriend(@Query("username") String username, @Query("nick") String nick);

    @POST(" api/Friend/addFriend")
    Observable<BaseBean<Boolean>> addFriend(@Query("usercode") String usercode, @Query("friendcode") String friendcode);
}

