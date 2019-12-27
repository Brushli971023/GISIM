package com.bawei6.baselibrary.basemvp;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description View层
 */
public interface IBaseView {
    /**
     * 显示正在加载view
     */
    void showLoading();
    /**
     * 关闭正在加载view
     */
    void hideLoading();
    /**
     * 显示提示
     * @param msg
     */
    void showToast(String msg);
    /**
     * 显示请求错误提示
     */
    void showError();
}
