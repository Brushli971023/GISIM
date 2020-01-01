package com.bawei6.baselibrary.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei6.baselibrary.basemvp.BasePresenter;
import com.bawei6.baselibrary.basemvp.IBaseModel;
import com.bawei6.baselibrary.basemvp.IBaseMvp;
import com.bawei6.baselibrary.basemvp.IBaseView;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description
 */
public abstract class BaseMvpActivity<M extends IBaseModel, V extends IBaseView, P extends BasePresenter> extends BaseActivity implements IBaseMvp<M,V,P> {
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //创建Presenter
        presenter = createPresenter();
        if (presenter != null){
            //将Model层注册到Presenter中
            presenter.registerModel(createModel());
            //将View层绑定到Presenter中
            presenter.attachView(createView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            //Activity销毁时的调用
            presenter.detachView();
        }
    }
}
