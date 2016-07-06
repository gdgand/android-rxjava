package com.gdgand.rxjava.tips.example.more;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.util.ActionSubscriber;
import rx.internal.util.InternalObservableUtils;

public class SafeObservable<T> extends Observable<T> {
    protected SafeObservable(OnSubscribe<T> f) {
        super(f);
    }

    public Subscription safeSubscribe(Subscriber<T> subscriber) {
        WeakSubscriberDecorator<T> weakSubscriberDecorator = new WeakSubscriberDecorator<>(subscriber);
        return subscribe(weakSubscriberDecorator);
    }

    public Subscription safeSubscribe(final Action1<? super T> onNext) {
        Action1<Throwable> onError = InternalObservableUtils.ERROR_NOT_IMPLEMENTED;
        Action0 onCompleted = Actions.empty();
        Subscriber<T> subscriber = new ActionSubscriber<T>(onNext, onError, onCompleted);
        WeakSubscriberDecorator<T> weakSubscriberDecorator = new WeakSubscriberDecorator<>(subscriber);
        return subscribe(weakSubscriberDecorator);
    }

    public Subscription safeSubscribe(final Action1<? super T> onNext, final Action1<Throwable> onError) {
        Action0 onCompleted = Actions.empty();
        Subscriber<T> subscriber = new ActionSubscriber<T>(onNext, onError, onCompleted);
        WeakSubscriberDecorator<T> weakSubscriberDecorator = new WeakSubscriberDecorator<>(subscriber);
        return subscribe(weakSubscriberDecorator);
    }

    public Subscription safeSubscribe(final Action1<? super T> onNext,
                                      final Action1<Throwable> onError, final Action0 onCompleted) {
        Subscriber<T> subscriber = new ActionSubscriber<T>(onNext, onError, onCompleted);
        WeakSubscriberDecorator<T> weakSubscriberDecorator = new WeakSubscriberDecorator<>(subscriber);
        return subscribe(weakSubscriberDecorator);
    }
}