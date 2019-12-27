package com.bawei6.baselibrary.basemvp;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description 持有V,M的引用
 */
public abstract class BasePresenter<M extends IBaseModel,V extends IBaseView> {
    /**
     * 持有UI接口的弱引用
     */
    protected WeakReference<V> mViewRef;
    protected M model;

    /**
     * 注册Model层
     *
     * @param model
     */
    public void registerModel(M model){
        this.model = model;
    }
    /**
     * 绑定View的方法
     * 在onCreate()中调用
     *
     * @param view
     */
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    /**
     * 解绑View的方法
     * 在onDestroy方法中调用，防止内存泄漏
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef = null;
            System.gc();
        }

        onViewDestroy();
    }

    /**
     * 释放资源处理
     */
    public abstract void onViewDestroy();


}
