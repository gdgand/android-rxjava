package com.androidhuman.example.rxjavainaction.rx;

import com.androidhuman.example.rxjavainaction.error.ContentNotAvailableError;
import com.androidhuman.example.rxjavainaction.model.Content;

import rx.Observable;
import rx.Subscriber;

final class ChecksAvailabilityOnSubscribe implements Observable.OnSubscribe<Void> {

    private final Content content;

    ChecksAvailabilityOnSubscribe(Content content) {
        this.content = content;
    }

    @Override
    public void call(Subscriber<? super Void> subscriber) {
        if (!subscriber.isUnsubscribed()) {
            if (content.isInService()) {
                // Content is available
                subscriber.onNext(null);
                subscriber.onCompleted();
            } else {
                // Content is not available; Not in service
                subscriber.onError(new ContentNotAvailableError());
            }
        }
    }
}
