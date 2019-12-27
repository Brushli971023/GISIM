package com.bawei6.baselibrary.basemvp;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description IBaseMvp用来创建Model、View和Presenter层的
 */
public interface IBaseMvp<M extends IBaseModel, V extends IBaseView, P extends BasePresenter> {

    M createModel();

    V createView();

    P createPresenter();
}
