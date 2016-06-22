package com.gdgand.rxjava.rxjavasample.operatorscreating;


import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;
import rx.util.async.Async;

/**
 * http://reactivex.io/documentation/operators.html#creating
 */
public class CreatingOperatorTest {

    private TestSubscriber subscriber;

    @Before
    public void setUp() throws Exception {
        subscriber = new TestSubscriber();
    }

    /**
     * https://github.com/square/retrofit/blob/d04f3a50e41ca01d22f370ac4f332f6ddf4ba9fe/retrofit-adapters/rxjava/src/main/java/retrofit2/adapter/rxjava/RxJavaCallAdapterFactory.java
     */
    @Test public void testCreateOperator() {
        System.out.print("testCreateOperator --> ");
        Observable<Integer> o = Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onCompleted();
            }
        });

        o.subscribe(i -> System.out.printf("%d ", i));
        System.out.println();
    }

    @Test public void testDeferOperator() {
        System.out.print("testDeferOperator --> ");

        DeferExample deferExample = new DeferExample();
        Observable<String> o = deferExample.nameObservable();
        deferExample.setName("name");
        o.subscribe(subscriber);
        subscriber.assertValue("name");
        o.subscribe(System.out::println);
    }

    @Test public void testEmptyOperator() {
        System.out.println("testEmptyOperator");
        Observable<Object> o = Observable.empty();
        o.subscribe(subscriber);
        subscriber.assertCompleted();
        subscriber.assertNoValues();
    }

    @Test public void testNeverOperator() {
        System.out.println("testNeverOperator");
        Observable o = Observable.never();
        o.subscribe(subscriber);
        subscriber.assertNotCompleted();
        subscriber.assertNoValues();
    }

    @Test public void testErrorOperator() {
        System.out.println("testErrorOperator");
        Observable<Object> o = Observable.error(new NullPointerException("error"));
        o.subscribe(subscriber);
        subscriber.assertError(NullPointerException.class);
        subscriber.assertNoValues();
    }

    /**
     * Javadoc: from(array)
     * Javadoc: from(Iterable)
     * Javadoc: from(Future)
     * Javadoc: from(Future,Scheduler)
     * Javadoc: from(Future,timout,timeUnit)
     */
    @Test public void testFromOperator() {
        System.out.print("testFromOperator --> ");

//        List<Integer> l = Arrays.asList(new Integer[]{1, 2, 3, 4, 5});
        Observable.from(Observable.range(0, 10).toBlocking().toIterable())
                .subscribe(i -> System.out.printf("%d ", i));
        System.out.println();
    }

    @Test public void testInterval() {
        System.out.print("testInterval --> ");
        TestScheduler scheduler = new TestScheduler();
        Observable<Long> o = Observable.interval(10, TimeUnit.MILLISECONDS, scheduler);
        o.subscribe(subscriber);
        scheduler.advanceTimeBy(100, TimeUnit.MILLISECONDS);
        List l = subscriber.getOnNextEvents();
        Observable.from(l).subscribe(i -> System.out.printf("%d ", i));
        System.out.println();
    }

    @Test public void testJustOperator() {
        System.out.print("testJustOperator --> ");
        Observable.just(1, 2, 3).subscribe(i -> System.out.printf("%d ", i));
        System.out.println();
    }

    @Test public void testRangeOperator() {
        System.out.print("testRangeOperator --> ");
        Observable.range(1, 20).subscribe(i -> System.out.printf("%d ", i));
        System.out.println();
    }

    @Test public void testRepeatOperator() {
        System.out.print("testRepeatOperator --> ");
        Observable.just(1, 2, 3).repeat(3).subscribe(i -> System.out.printf("%d ", i));
        System.out.println();
    }

    /**
     * RxJava
     * asyncAction
     * asyncFunc
     * deferFuture
     * forEachFuture
     * fromAction
     * fromCallable
     * fromFunc0
     * fromRunnable
     * start
     * startFuture
     * toAsync
     */
    @Test public void testStartOperator() {
        System.out.println("testStartOperator");
        Observable<Integer> o = Async.start(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        o.subscribe(subscriber);
        subscriber.awaitTerminalEvent();
        subscriber.assertCompleted();
        subscriber.assertValue(1);
    }

    /**
     * https://github.com/ReactiveX/RxJava/wiki/Creating-Observables
     */
    @Test public void testTimerOperator() {
        System.out.println("testTimerOperator");
        TestScheduler testScheduler = new TestScheduler();
        Observable<Long> o = Observable.timer(100, TimeUnit.MILLISECONDS, testScheduler);
        o.subscribe(System.out::print);
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS);
    }
}