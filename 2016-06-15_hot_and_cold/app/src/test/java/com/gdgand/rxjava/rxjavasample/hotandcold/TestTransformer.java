package com.gdgand.rxjava.rxjavasample.hotandcold;


import rx.Observable;

public class TestTransformer {

    // Observable 을 그대로 리턴하는 Transformer
    public static <T> Observable.Transformer<T, T> pass() {
        return observable -> observable;
    }
}
