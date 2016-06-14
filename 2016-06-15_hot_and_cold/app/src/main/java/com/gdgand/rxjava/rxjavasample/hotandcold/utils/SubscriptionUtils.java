package com.gdgand.rxjava.rxjavasample.hotandcold.utils;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * https://gist.github.com/ypresto/accec4409654a1830f54
 */
public class SubscriptionUtils {
    /**
     * Automatically adds to / removes from specified {@link CompositeSubscription} when subscribe / unsubscribe is called.
     * Useful when subscribing observable repeatedly, as you do not need to manipulate CompositeSubscription manually.
     */
    public static <T> Observable.Operator<T, T> composite(CompositeSubscription subscriptions) {
        return subscriber -> {
			subscriptions.add(subscriber);
			subscriber.add(Subscriptions.create(() -> subscriptions.remove(subscriber)));
			return subscriber;
		};
    }
}