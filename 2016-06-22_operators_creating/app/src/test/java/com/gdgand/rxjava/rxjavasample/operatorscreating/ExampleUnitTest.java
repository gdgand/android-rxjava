package com.gdgand.rxjava.rxjavasample.operatorscreating;


import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Observable<Integer> o = Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            subscriber.onNext(1);
            subscriber.onNext(2);
            subscriber.onNext(3);
            subscriber.onNext(4);
            subscriber.onNext(5);
            subscriber.onCompleted();
        });

        TestSubscriber<Integer> t = new TestSubscriber<>();
        o.subscribe(System.out::println);
    }
}