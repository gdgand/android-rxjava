package com.gdgand.rxjava.tips.example.more;

import java.lang.ref.WeakReference;

import rx.Subscriber;

public class WeakSubscriberDecorator<T> extends Subscriber<T> {
    private final WeakReference<Subscriber<T>> mWeakSubscriber;

    public WeakSubscriberDecorator(Subscriber<T> subscriber) {
        this.mWeakSubscriber = new WeakReference<>(subscriber);
    }

    @Override
    public void onCompleted() {
        Subscriber<T> subscriber = mWeakSubscriber.get();
        if (subscriber != null) subscriber.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        Subscriber<T> subscriber = mWeakSubscriber.get();
        if (subscriber != null) subscriber.onError(e);
    }

    @Override
    public void onNext(T t) {
        Subscriber<T> subscriber = mWeakSubscriber.get();
        if (subscriber != null) subscriber.onNext(t);
    }
}
