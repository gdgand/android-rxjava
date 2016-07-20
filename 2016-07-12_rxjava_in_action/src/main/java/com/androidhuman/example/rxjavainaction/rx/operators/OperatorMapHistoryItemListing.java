package com.androidhuman.example.rxjavainaction.rx.operators;

import com.androidhuman.example.rxjavainaction.model.HistoryItem;
import com.androidhuman.example.rxjavainaction.model.Listing;

import java.util.Locale;

import rx.Observable;
import rx.Subscriber;

public class OperatorMapHistoryItemListing implements Observable.Operator<Listing, HistoryItem> {

    @Override
    public Subscriber<? super HistoryItem> call(final Subscriber<? super Listing> o) {
        return new Subscriber<HistoryItem>() {

            @Override
            public void onCompleted() {
                o.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                o.onError(e);
            }

            @Override
            public void onNext(HistoryItem t) {
                if (null != t) {
                    o.onNext(new Listing(t.getTitle(), null,
                            String.format(Locale.US,
                                    "gdg://korea.android/listing/%d", t.getItemId())));
                }
            }
        };
    }
}
