package com.bawei6.homemodule.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.bawei6.baselibrary.base.BaseMvpActivity;
import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.homemodule.R;
import com.bawei6.homemodule.contract.HomeContract;
import com.bawei6.homemodule.model.HomeModel;
import com.bawei6.homemodule.presenter.HomePresenter;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description 主页面
 */
@Route(path = BaseConstant.AROUTERTO_HOMEMODULE)
public class HomeActivity extends BaseMvpActivity<HomeModel, HomeContract.View,HomePresenter> implements HomeContract.View {
    MapView mMapView = null;
    AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ARouter.getInstance().inject(this);
        init();

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.home_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
    }

    private void init() {
        if (presenter != null){

        }
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
    public void setData(String str) {

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
}
