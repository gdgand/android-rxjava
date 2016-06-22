package com.gdgand.rxjava.rxjavasample.operatorscreating;

import org.junit.Test;

import java.util.concurrent.Executors;

import rx.Observable;
import rx.internal.schedulers.ExecutorScheduler;
import rx.schedulers.Schedulers;

/**
 * Created by engeng on 6/21/16.
 */
public class SubscribeOnObserveOn {
    @Test public void testSubscribeOnObserveOnBasic() throws InterruptedException {
        Observable<Integer> o = Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            printCurrentThreadName("create");
            subscriber.onNext(1);
            subscriber.onCompleted();
        }).observeOn(Schedulers.io());
        o.map(i -> printCurrentThreadName("map1"))
                .observeOn(Schedulers.newThread())
                .map(i -> printCurrentThreadName("map2"))
                .subscribe(i -> printCurrentThreadName("subscribe"));
        Thread.sleep(100);
    }

    @Test public void testSubscribeOnObserveOn() throws InterruptedException {
        System.out.println(printCurrentThreadName("function start"));

        Observable<Integer> o1 = Observable.just(0).subscribeOn(new ExecutorScheduler(Executors.newSingleThreadExecutor()));
        o1.observeOn(Schedulers.io())
                .map(i -> printCurrentThreadName("first map operator"))
                .observeOn(Schedulers.newThread())
                .map(i -> printCurrentThreadName("second map operator"))
                .subscribe(i -> printCurrentThreadName("first subscription"));
        o1.subscribe(i -> printCurrentThreadName("second subscription"));
        Thread.sleep(100);
    }

    private int printCurrentThreadName(String s) {
        System.out.printf("%s --> %s\n", s, getCurrentThreadName());
        return 0;
    }

    private String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }
}
