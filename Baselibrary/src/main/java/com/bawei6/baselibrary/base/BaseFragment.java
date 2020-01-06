package com.bawei6.baselibrary.base;

import com.bawei6.baselibrary.basemvp.BasePresenter;
import com.bawei6.baselibrary.basemvp.IBaseModel;
import com.bawei6.baselibrary.basemvp.IBaseView;

/**
 * @author AZhung
 * @date 2020/1/5
 * @description
 */
public class BaseFragment extends BaseMvpFragment implements IBaseView {
    @Override
    public IBaseModel createModel() {
        return null;
    }

    @Override
    public IBaseView createView() {
        return null;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showError() {

    }
}
