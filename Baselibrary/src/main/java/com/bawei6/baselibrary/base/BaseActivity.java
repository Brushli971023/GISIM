package com.bawei6.baselibrary.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei6.baselibrary.basemvp.BasePresenter;
import com.bawei6.baselibrary.basemvp.IBaseModel;
import com.bawei6.baselibrary.basemvp.IBaseView;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description
 */
public class BaseActivity extends BaseMvpActivity implements IBaseView {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        immersiveLayout();
    }

    /**
     * 沉浸式布局
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void immersiveLayout() {

        Window window = getWindow();
        View decorView = window.getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
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
}
