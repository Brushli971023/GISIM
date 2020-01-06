package com.bawei6.usermodule.model.callback;

import com.bawei6.usermodule.model.bean.UserInfoBean;

import java.util.List;

/**
 * @author AZhung
 * @date 2019/12/31
 * @description
 */
public interface UserCallBack {
    void onSuccess(String string);
    void onSuccess(List<UserInfoBean> userInfoBeanList);
    void onFaile(Throwable e);
}

