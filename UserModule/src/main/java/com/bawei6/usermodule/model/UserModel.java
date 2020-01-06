package com.bawei6.usermodule.model;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
                username,
                password,
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
                    BaseBean<UserInfoBean> userInfoBeanBaseBean = (BaseBean<UserInfoBean>) o;
                    if (userInfoBeanBaseBean.getData() != null){
                        userCallBack.onSuccess(userInfoBeanBaseBean.getMsg());
                    }else {
                        userCallBack.onSuccess("注册失败");
                    }
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
        BaseObservable.doObservable(userApi.getLogin(new UserLoginBean(username,password)),loginIM(username,password),new BaseObserver(){

            @Override
            public void onNext(Object o) {
                super.onNext(o);
                if (o instanceof BaseBean){
                    BaseBean<UserInfoBean> userInfoBeanBaseBean = (BaseBean<UserInfoBean>) o;
                    if (((BaseBean<UserInfoBean>) o).getCode() == 200){
                        callBack.onSuccess(userInfoBeanBaseBean.getData().getUsercode());
                    }else {
                        callBack.onSuccess("登录失败");
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callBack.onFaile(e);
            }
        });

    }

    @Override
    public void searchFriend(String username, String nick, final UserCallBack callBack) {
        BaseObservable.doObservable(userApi.searchFriend(username,nick),new BaseObserver<BaseBean<List<UserInfoBean>>>(){
            @Override
            public void onNext(BaseBean<List<UserInfoBean>> listBaseBean) {
                super.onNext(listBaseBean);
                if (listBaseBean.getData() != null){
                    callBack.onSuccess(listBaseBean.getData());
                }else {
                    callBack.onSuccess(listBaseBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callBack.onFaile(e);
            }
        });
    }

    @Override
    public void addFriend(String usercode, String friendcode, String username, final UserCallBack callBack) {
        BaseObservable.doObservable(userApi.addFriend(usercode,friendcode),addFriednIM(username),new BaseObserver(){
            @Override
            public void onNext(Object o) {
                super.onNext(o);
                if (o instanceof BaseBean){
                    BaseBean<Boolean> baseBean = (BaseBean) o;
                    if (baseBean.getData()){
                        callBack.onSuccess("添加成功");
                    }else {
                        callBack.onSuccess("添加失败");
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callBack.onFaile(e);
            }
        });
    }

    @Override
    public void getFriends(String usercode, final UserCallBack callBack) {
        BaseObservable.doObservable(userApi.getFriends(usercode),new BaseObserver<BaseBean<List<UserInfoBean>>>(){
            @Override
            public void onNext(BaseBean<List<UserInfoBean>> listBaseBean) {
                super.onNext(listBaseBean);
                if (listBaseBean.getData() != null){
                    callBack.onSuccess(listBaseBean.getData());
                }else {
                    callBack.onSuccess("好友列表为空");
                }

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                callBack.onFaile(e);
            }
        });

    }

    private Observable<Boolean> addFriednIM(final String tousername) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                XmppManager.getInstance().getXmppFriendManager().addFriend(tousername+"@"+XmppManager.getInstance().getXmppConfig().getDomainName(), tousername);
                emitter.onNext(true);
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

    @SuppressLint("CheckResult")
    private void test(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                for (int i=0; i < 10; i++){
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }).subscribe(new Observer<Object>() {

            @Override
            public void onSubscribe(Disposable d) {
                d.dispose();
            }

            @Override
            public void onNext(Object o) {
                Log.d("rxjava----",o.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        ArrayList<String> strings = new ArrayList<>();
        Observable.fromIterable(strings)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("rxjava----",s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("rxjava----","fromIterable...complete");
                    }
                });
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d("rxjava----",String.valueOf(aLong));
                    }
                });

        Observable.fromArray(strings)
                .subscribe(new Observer<ArrayList<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<String> strings) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Observable.intervalRange(2,3,4,10,TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Observable.range(2,6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("rxjava----",String.valueOf(integer));
                    }
                });
        
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                emitter.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return null;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {

                return null;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observable.just(1,2,3,4,5,6)
                .buffer(3)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d("rxjava___bffer+++",integers.size()+"");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("rxjava___bffer+++","complete");
                    }
                });

    }


}
