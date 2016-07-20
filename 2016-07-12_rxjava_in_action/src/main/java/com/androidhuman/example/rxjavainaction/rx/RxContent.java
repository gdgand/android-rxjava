package com.androidhuman.example.rxjavainaction.rx;

import com.androidhuman.example.rxjavainaction.model.Content;
import com.androidhuman.example.rxjavainaction.model.User;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import rx.Observable;

public final class RxContent {

    @CheckResult
    @NonNull
    public static Observable<Void> checksAvailability(Content content) {
        return Observable.create(new ChecksAvailabilityOnSubscribe(content));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> checksBalance(Content content, User user) {
        return Observable.create(new ChecksBalanceOnSubscribe(content, user));
    }

    @CheckResult
    @NonNull
    public static Observable<Void> checksContentRating(Content content, User user) {
        return Observable.create(new ChecksContentRatingOnSubscribe(content, user));
    }

}
