package com.gdgand.rxjava.rxjavasample.operatorscreating;

import rx.Observable;

/**
 * Created by engeng on 6/21/16.
 */
public class DeferExample {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Observable<String> nameObservable() {
//        return Observable.just(name);
        return Observable.defer(() -> Observable.just(name));
    }
}
