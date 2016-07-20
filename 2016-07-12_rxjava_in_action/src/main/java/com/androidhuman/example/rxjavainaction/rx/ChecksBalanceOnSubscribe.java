package com.androidhuman.example.rxjavainaction.rx;

import com.androidhuman.example.rxjavainaction.error.InsufficientBalanceError;
import com.androidhuman.example.rxjavainaction.model.Content;
import com.androidhuman.example.rxjavainaction.model.User;

import rx.Observable;
import rx.Subscriber;

final class ChecksBalanceOnSubscribe implements Observable.OnSubscribe<Void> {

    private final Content content;

    private final User user;

    ChecksBalanceOnSubscribe(Content content, User user) {
        this.content = content;
        this.user = user;
    }

    @Override
    public void call(Subscriber<? super Void> subscriber) {
        if (!subscriber.isUnsubscribed()) {
            if (content.getPrice() <= user.getBalance()) {
                // Can purchase a content
                subscriber.onNext(null);
                subscriber.onCompleted();
            } else {
                // Cannot purchase a content; balance is low
                subscriber.onError(
                        new InsufficientBalanceError(content.getPrice() - user.getBalance()));
            }
        }
    }
}
