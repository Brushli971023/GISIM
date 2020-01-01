package com.bawei6.baselibrary.base;

import com.bawei6.baselibrary.data.BaseObserver;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description
 */
public class BaseObservable {
    public static <T> void doObservable(Observable<T> observable, BaseObserver<T> observer){
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
