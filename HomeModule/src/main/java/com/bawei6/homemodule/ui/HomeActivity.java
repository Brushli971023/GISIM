package com.bawei6.homemodule.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.bawei6.baselibrary.base.BaseActivity;
import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.homemodule.R;
import com.bawei6.homemodule.contract.HomeContract;
import com.bawei6.homemodule.model.HomeModel;
import com.bawei6.homemodule.presenter.HomePresenter;
import com.bawei6.homemodule.ui.adapter.HomeTimeRecyclerAdapter;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description 主页面
 */
@Route(path = BaseConstant.AROUTERTO_HOMEMODULE)
public class HomeActivity extends BaseActivity implements HomeContract.View {
    @Autowired(name = "username")
    public String username;
    private boolean visibleFlag = true;
    private ImageView navigation_image_first;
    private ImageView navigation_image_second;
    private ImageView navigation_image_third;
    private ImageView navigation_image_fourth;
    private ImageView navigation_mid_image;
    private RelativeLayout bottom_navigation;
    private RecyclerView home_sideTimeList;
    private ImageView time_list_exit;
    private RelativeLayout time_list;

    MapView mMapView = null;
    AMap aMap;
    private TextView text_time_list_exit;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ARouter.getInstance().inject(this);
        Toast.makeText(this, username + "，欢迎您登录图聊", Toast.LENGTH_SHORT).show();
        initView();
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.home_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
            //去掉地图右下角缩放按钮
            aMap.getUiSettings().setZoomControlsEnabled(false);
        }
        initEvent();
    }

    private void initEvent() {
        bottom_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(BaseConstant.AROUTERTO_PHONECONTRACT).navigation();
            }
        });

        time_list_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_list.setVisibility(View.GONE);
                text_time_list_exit.setVisibility(View.VISIBLE);
            }
        });

        text_time_list_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_list.setVisibility(View.VISIBLE);
                text_time_list_exit.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        bottom_navigation = findViewById(R.id.bottom_navigation);
        navigation_image_first = findViewById(R.id.navigation_image_first);
        navigation_image_second = findViewById(R.id.navigation_image_second);
        navigation_image_third = findViewById(R.id.navigation_image_third);
        navigation_image_fourth = findViewById(R.id.navigation_image_fourth);
        navigation_mid_image = findViewById(R.id.navigation_mid_image);
        home_sideTimeList = (RecyclerView) findViewById(R.id.home_sideTimeList);
        time_list_exit = (ImageView) findViewById(R.id.time_list_exit);
        time_list = (RelativeLayout) findViewById(R.id.time_list);
        text_time_list_exit = (TextView) findViewById(R.id.text_time_list_exit);


        home_sideTimeList.setLayoutManager(new LinearLayoutManager(this));
        home_sideTimeList.setAdapter(new HomeTimeRecyclerAdapter(this));
    }


    @Override
    public HomeModel createModel() {
        return new HomeModel();
    }

    @Override
    public HomeContract.View createView() {
        return this;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    @Override
    public void setData(String str) {

    }
}
