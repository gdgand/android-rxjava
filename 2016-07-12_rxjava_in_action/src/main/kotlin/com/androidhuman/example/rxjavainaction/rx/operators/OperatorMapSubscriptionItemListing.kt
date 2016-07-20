package com.androidhuman.example.rxjavainaction.rx.operators

import com.androidhuman.example.rxjavainaction.model.Listing
import com.androidhuman.example.rxjavainaction.model.SubscriptionItem
import rx.Observable
import rx.Subscriber

class OperatorMapSubscriptionItemListing : Observable.Operator<Listing, SubscriptionItem> {

    override fun call(o: Subscriber<in Listing>): Subscriber<in SubscriptionItem> {
        return object: Subscriber<SubscriptionItem>() {

            override fun onError(e: Throwable?) {
                o.onError(e)
            }

            override fun onCompleted() {
                o.onCompleted()
            }

            override fun onNext(t: SubscriptionItem?) {
                t?.let {
                    o.onNext(Listing(t.title, t.channelName,
                            "gdg://korea.android/listing/${t.itemId}?channelName=${t.channelName}"))
                }
            }
        }
    }
}