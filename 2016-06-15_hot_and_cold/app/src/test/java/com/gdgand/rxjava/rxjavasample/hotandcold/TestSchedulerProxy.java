package com.gdgand.rxjava.rxjavasample.hotandcold;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.TestScheduler;

import java.util.concurrent.TimeUnit;

public class TestSchedulerProxy {

    private static final TestScheduler SCHEDULER = new TestScheduler();
    private static final TestSchedulerProxy INSTANCE = new TestSchedulerProxy();

    static {
        try {
            RxJavaPlugins.getInstance().registerSchedulersHook(new RxJavaSchedulersHook() {
                @Override
                public Scheduler getIOScheduler() {
                    return SCHEDULER;
                }

                @Override
                public Scheduler getComputationScheduler() {
                    return SCHEDULER;
                }

                @Override
                public Scheduler getNewThreadScheduler() {
                    return SCHEDULER;
                }
            });

            RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
                @Override
                public Scheduler getMainThreadScheduler() {
                    return SCHEDULER;
                }
            });
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Schedulers class already initialized. " +
                    "Ensure you always use the TestSchedulerProxy in unit tests.");
        }
    }

    public static TestSchedulerProxy get() {
        return INSTANCE;
    }

    public void advanceBy(long delayTime, TimeUnit unit) {
        SCHEDULER.advanceTimeBy(delayTime, unit);
    }

}