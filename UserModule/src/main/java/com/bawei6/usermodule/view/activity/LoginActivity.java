package com.bawei6.usermodule.view.activity;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei6.baselibrary.base.BaseActivity;
import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.baselibrary.utils.ThreadUtils;
import com.bawei6.baselibrary.utils.UIToastUtils;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.contract.UserContract;
import com.bawei6.usermodule.model.UserModel;
import com.bawei6.usermodule.model.api.UserApi;
import com.bawei6.usermodule.model.bean.UserInfoBean;
import com.bawei6.usermodule.presenter.UserPresenter;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;

import java.util.List;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description 登录注册页面
 */
@Route(path = BaseConstant.AROUTERTO_USERMODULE)
public class LoginActivity extends BaseActivity implements UserContract.View,View.OnClickListener {

    private Button btu_register;
    private Button btu_login;
    private XmppManager mXmppManager;
    private UserApi userApi;
    private EditText edit_username;
    private EditText edit_password;
    private CheckBox checkbox_remember_pswd;
    private String username;
    private String password;
    private UserPresenter userPresenter;
    private Toast loginSuccess;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rl);
        ARouter.getInstance().inject(this);
        UIToastUtils.getInstance().init(this);
        mXmppManager = XmppManager.getInstance();
        initView();
    }



    private void initView() {
        btu_register = (Button) findViewById(R.id.btu_register);
        btu_login = (Button) findViewById(R.id.btu_login);

        btu_register.setOnClickListener(this);
        btu_login.setOnClickListener(this);

        edit_username = (EditText) findViewById(R.id.edit_username);
        edit_username.setOnClickListener(this);
        edit_password = (EditText) findViewById(R.id.edit_password);
        edit_password.setOnClickListener(this);
        checkbox_remember_pswd = (CheckBox) findViewById(R.id.checkbox_remember_pswd);
        checkbox_remember_pswd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btu_register) {
            submit();
            if (userPresenter != null){
                userPresenter.registerUser(username,password);
            }
        } else if (id == R.id.btu_login) {
            submit();
            if (userPresenter != null){
                userPresenter.loginUser(username,password);
            }
        }
    }



    private void submit() {
        // validate
        username = edit_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        password = edit_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public UserModel createModel() {
        return new UserModel();
    }

    @Override
    public UserContract.View createView() {
        return this;
    }

    @Override
    public UserPresenter createPresenter() {
        userPresenter = new UserPresenter();
        return userPresenter;
    }

    @Override
    public void registerResult(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginResult(String string) {
            BaseConstant.userCode = string;
            loginSuccess = Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT);
            BaseConstant.userName = username;
            ARouter.getInstance().build(BaseConstant.AROUTERTO_HOMEMODULE).withString("username",username).navigation();
    }

    @Override
    public void searchResult(List<UserInfoBean> userInfoBeanList) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (loginSuccess != null){
            loginSuccess.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userPresenter.onViewDestroy();
        userPresenter.detachView();
    }
}
