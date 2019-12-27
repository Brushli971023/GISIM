package com.bawei6.baselibrary;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bawei6.baselibrary.basemvp.BasePresenter;
import com.bawei6.baselibrary.basemvp.IBaseModel;
import com.bawei6.baselibrary.basemvp.IBaseMvp;
import com.bawei6.baselibrary.basemvp.IBaseView;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description
 */
public abstract class BaseMvpFragment <M extends IBaseModel, V extends IBaseView, P extends BasePresenter> extends Fragment implements IBaseMvp<M,V,P> {
    protected P presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

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
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null){
            //Activity销毁时的调用
            presenter.detachView();
        }
    }
}
