package com.bawei6.usermodule.model.callback;

/**
 * @author AZhung
 * @date 2019/12/31
 * @description
 */
public interface UserCallBack {
    void onSuccess(String string);
    void onFaile(Throwable e);
}

