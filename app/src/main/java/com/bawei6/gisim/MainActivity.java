package com.bawei6.gisim;

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
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btu_toMap;
    private Button btu_toIM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        btu_toMap = (Button) findViewById(R.id.btu_toMap);
        btu_toIM = (Button) findViewById(R.id.btu_toIM);

        btu_toMap.setOnClickListener(this);
        btu_toIM.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btu_toMap:
                ARouter.getInstance().build(BaseConstant.AROUTERTO_HOMEMODULE).navigation();
                break;
            case R.id.btu_toIM:
                ARouter.getInstance().build(BaseConstant.AROUTERTO_USERMODULE).navigation();
                break;
        }
    }
}
