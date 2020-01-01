package com.bawei6.homemodule.model;

import android.util.Log;

import com.bawei6.homemodule.contract.HomeContract;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description
 */
public class HomeModel implements HomeContract.Model {
    /**
     * 停止请求
     */
    public void stopRequest(){
        Log.i("HomeModel","stop request...");
    }

}
