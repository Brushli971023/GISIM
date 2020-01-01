package com.bawei6.baselibrary.data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
/**
 * @author AZhung
 * @date 2019/12/27
 * @description Api服务
 */
public interface BaseApiService {
    @FormUrlEncoded
    @POST("token")
    Call<TokenBean> getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);
}
