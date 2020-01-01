package com.bawei6.homemodule.presenter;

import com.bawei6.baselibrary.basemvp.BasePresenter;
import com.bawei6.homemodule.contract.HomeContract;
import com.bawei6.homemodule.model.HomeModel;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description 主页模块Presenter层
 */
public class HomePresenter extends BasePresenter<HomeModel, HomeContract.View> {


    /**
     * 销毁Activity时的操作，可以停止当前model
     */
    @Override
    public void onViewDestroy() {
        if (model != null){
            model.stopRequest();
        }
    }

}
