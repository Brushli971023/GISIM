package com.bawei6.usermodule;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei6.baselibrary.base.BaseMvpActivity;
import com.bawei6.baselibrary.base.BaseObservable;
import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.baselibrary.data.BaseBean;
import com.bawei6.baselibrary.data.BaseObserver;
import com.bawei6.baselibrary.utils.RetrofitUtils;
import com.bawei6.baselibrary.utils.ThreadUtils;
import com.bawei6.baselibrary.utils.UIToastUtils;
import com.bawei6.usermodule.contract.UserContract;
import com.bawei6.usermodule.model.UserModel;
import com.bawei6.usermodule.model.api.UserApi;
import com.bawei6.usermodule.model.bean.UserInfoBean;
import com.bawei6.usermodule.model.bean.UserLoginBean;
import com.bawei6.usermodule.presenter.UserPresenter;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description 登录注册页面
 */
@Route(path = BaseConstant.AROUTERTO_USERMODULE)
public class RLActivity extends BaseMvpActivity<UserModel, UserContract.View, UserPresenter> implements UserContract.View,View.OnClickListener {

    private Button btu_register;
    private Button btu_login;
    private XmppManager mXmppManager;
    private UserApi userApi;
    private EditText edit_username;
    private EditText edit_password;
    private CheckBox checkbox_remember_pswd;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rl);
        ARouter.getInstance().inject(this);
        UIToastUtils.getInstance().init(this);
        initListener();
        initView();
    }

    private void initListener() {

        ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                mXmppManager = XmppManager.getInstance();
                mXmppManager.addMessageListener(new IMsgCallback() {
                    @Override
                    public void Success(MsgEntity msgEntity) {
                        UIToastUtils.getInstance().uiToast(msgEntity.getFrom() + ":" + msgEntity.getMsg());
                    }

                    @Override
                    public void Failed(Throwable throwable) {

                    }
                });
            }
        });
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
            if (presenter != null){
                presenter.registerUser(username,password);
            }
        } else if (id == R.id.btu_login) {
            submit();
            if (presenter != null){
                presenter.loginUser(username,password);
            }
        }
//        else if (id == R.id.btu_add) {
//            ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
//                @Override
//                public void run() {
//                    if (mXmppManager.getXmppFriendManager().addFriend(tousername+"@"+XmppManager.getInstance().getXmppConfig().getDomainName(), tousername)){
//                        UIToastUtils.getInstance().uiToast("添加成功");
//                    }else {
//                        UIToastUtils.getInstance().uiToast("添加失败");
//                    }
//                }
//            });
//        }else if (id == R.id.btu_to) {
//            ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
//                @Override
//                public void run() {
//                    mXmppManager.getXmppMsgManager().sendSingleMessage(tousername+"@"+XmppManager.getInstance().getXmppConfig().getDomainName(),"您好"+tousername);
//                }
//            });
//        }
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
        return new UserPresenter();
    }

    @Override
    public void registerResult(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginResult(String string) {
        if ("登录成功".equals(string)){
            Toast.makeText(RLActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(BaseConstant.AROUTERTO_HOMEMODULE).navigation();
        }else {
            Toast.makeText(RLActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }
}
