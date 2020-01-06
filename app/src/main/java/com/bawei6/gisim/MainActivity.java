package com.bawei6.gisim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei6.baselibrary.common.BaseConstant;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description
 */
public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this,XmppService.class));
        //直接进入登录页面
        ARouter.getInstance().build(BaseConstant.AROUTERTO_USERMODULE).navigation();
    }
}
