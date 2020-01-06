package com.bawei6.gisim;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei6.baselibrary.deviceinfo.AppInfoConfig;
import com.bawei6.baselibrary.deviceinfo.DeviceInfoConfig;
import com.bawei6.baselibrary.utils.AliyunUtils;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description
 */
public class MainApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //拆分dex文件
        initMutilDex();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化ARouter
        initARouter();
        //初始化设备信息获取类
        initInfoConfig();

        AliyunUtils.getInstance().init(this);
    }

    private void initMutilDex() {
        MultiDex.install(this);
    }

    private void initInfoConfig() {
        AppInfoConfig.getInstance().init(this);
        DeviceInfoConfig.getInstance().init(this);
    }

    private void initARouter() {
        // 必须在初始化ARouter之前配置
        if (BuildConfig.DEBUG){
            // 日志开启
            ARouter.openLog();
            // 调试模式开启，如果在install run模式下运行，则必须开启调试模式
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
