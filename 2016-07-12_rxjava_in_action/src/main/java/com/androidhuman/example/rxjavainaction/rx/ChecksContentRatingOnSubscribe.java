package com.androidhuman.example.rxjavainaction.rx;

import com.androidhuman.example.rxjavainaction.error.ContentRatingError;
import com.androidhuman.example.rxjavainaction.model.Content;
import com.androidhuman.example.rxjavainaction.model.User;

import rx.Observable;
import rx.Subscriber;

final class ChecksContentRatingOnSubscribe implements Observable.OnSubscribe<Void> {

    private final Content content;

    private final User user;

    ChecksContentRatingOnSubscribe(Content content, User user) {
        this.content = content;
        this.user = user;
    }

    @Override
    public void call(Subscriber<? super Void> subscriber) {
        if (!subscriber.isUnsubscribed()) {
            if (content.isAdult() && user.isAdult()) {
                // Content is available
                subscriber.onNext(null);
                subscriber.onCompleted();
            } else {
                // Content is not available; Rating is not suitable for user
                subscriber.onError(new ContentRatingError());
            }
        }
    }
}
