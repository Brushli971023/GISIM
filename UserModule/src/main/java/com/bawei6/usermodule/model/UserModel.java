package com.bawei6.usermodule.model;

import android.util.Log;

import com.bawei6.baselibrary.base.BaseObservable;
import com.bawei6.baselibrary.data.BaseBean;
import com.bawei6.baselibrary.data.BaseObserver;
import com.bawei6.baselibrary.utils.RetrofitUtils;
import com.bawei6.usermodule.contract.UserContract;
import com.bawei6.usermodule.model.api.UserApi;
import com.bawei6.usermodule.model.bean.UserInfoBean;
import com.bawei6.usermodule.model.bean.UserLoginBean;
import com.bawei6.usermodule.model.callback.UserCallBack;
import com.baweigame.xmpplibrary.XmppManager;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author AZhung
 * @date 2019/12/31
 * @description
 */
public class UserModel implements UserContract.Model {
    private final UserApi userApi;

    public UserModel() {
        userApi = RetrofitUtils.getInstance().create(UserApi.class);
    }

    /**
     * 停止请求
     */
    public void stopRequest(){
        Log.i("HomeModel","stop request...");
    }

    @Override
    public void registerUserFromNet(String username, String password, final UserCallBack userCallBack) {
        BaseObservable.doObservable(Observable.merge(registerIM(username, password), userApi.getRegister(new UserInfoBean(1,
                "123",
                "123",
                "123",
                "123",
                "123",
                "123",
                "123",
                9,
                "123",
                "123",
                12,
                13))), new BaseObserver<Object>() {
            @Override
            public void onNext(Object o) {
                super.onNext(o);
                if (o instanceof BaseBean) {
                    userCallBack.onSuccess("注册成功");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                userCallBack.onFaile(e);
            }
        });
    }

    @Override
    public void loginFromNet(String username, String password, final UserCallBack callBack) {
        BaseObservable.doObservable(Observable.merge(loginIM(username, password), userApi.getLogin(new UserLoginBean(username, password))), new BaseObserver<Object>() {
            @Override
            public void onNext(Object o) {
                super.onNext(o);
                if (o instanceof BaseBean) {
                    callBack.onSuccess("登录成功");
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callBack.onFaile(e);
            }
        });
    }


    private Observable<Boolean> registerIM(final String username, final String pwssword) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                XmppManager.getInstance().getXmppUserManager().createAccount(username, pwssword);
                emitter.onNext(true);
            }
        });
    }

    private Observable<Boolean> loginIM(final String username, final String pwssword) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                XmppManager.getInstance().getXmppUserManager().login(username, pwssword);
                emitter.onNext(true);
            }
        });
    }
}
