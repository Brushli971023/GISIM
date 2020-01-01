package com.bawei6.baselibrary.utils;

import android.app.Activity;
import android.widget.Toast;
/**
 * @author AZhung
 * @date 2019/12/27
 * @description Toast工具类
 */
public class UIToastUtils {
    private Activity activity;

    private static volatile UIToastUtils singleton;

    private UIToastUtils() {
    }

    public static UIToastUtils getInstance() {
        if (singleton == null) {
            synchronized (UIToastUtils.class) {
                if (singleton == null) {
                    singleton = new UIToastUtils();
                }
            }
        }
        return singleton;
    }


    public void init(Activity activity){
        this.activity = activity;
    }


    public void uiToast(final String string){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, string, Toast.LENGTH_SHORT).show();
            }
        });
    }
}