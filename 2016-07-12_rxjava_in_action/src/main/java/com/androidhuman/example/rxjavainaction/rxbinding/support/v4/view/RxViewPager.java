package com.androidhuman.example.rxjavainaction.rxbinding.support.v4.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import rx.Observable;

import static com.jakewharton.rxbinding.internal.Preconditions.checkNotNull;

public final class RxViewPager {

    /**
     * Create an observable of scroll state change events on {@code view}.
     * <p>
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     * <p>
     */
    @CheckResult
    @NonNull
    public static Observable<Integer> pageScrollStateChanges(@NonNull ViewPager view) {
        checkNotNull(view, "view == null");
        return Observable.create(new ViewPagerPageScrollStateChangeOnSubscribe(view));
    }

}
