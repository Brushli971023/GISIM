package com.bawei6.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
/**
 * @author AZhung
 * @date 2019/12/27
 * @description Toast工具类
 */
public class UIToastUtils {
    private Context context;
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


    public void init(Context context){
        this.context = context;
    }

    public void init(Activity activity){
        this.activity = activity;
    }


    public void uiToast(final String string){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void shortToast(String string){
        Toast.makeText(context,string,Toast.LENGTH_SHORT).show();
    }

    public void longToast(String string){
        Toast.makeText(context,string,Toast.LENGTH_LONG).show();
    }
}