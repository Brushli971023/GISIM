package com.bawei6.homemodule.presenter;

import com.bawei6.baselibrary.basemvp.BasePresenter;
import com.bawei6.homemodule.contract.HomeContract;
import com.bawei6.homemodule.model.HomeModel;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description
 */
public class HomePresenter extends BasePresenter<HomeModel, HomeContract.View> {

    @Override
    public void onViewDestroy() {

    }
}
