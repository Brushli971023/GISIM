package com.bawei6.baselibrary.data;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description observer基类
 */
public class BaseObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}