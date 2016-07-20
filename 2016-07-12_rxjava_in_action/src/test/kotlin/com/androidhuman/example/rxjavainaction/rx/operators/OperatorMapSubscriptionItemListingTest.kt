package com.androidhuman.example.rxjavainaction.rx.operators

import com.androidhuman.example.rxjavainaction.model.Listing
import com.androidhuman.example.rxjavainaction.model.SubscriptionItem
import org.junit.Assert.assertEquals
import org.junit.Test
import rx.Observable
import rx.observers.TestSubscriber

class OperatorMapSubscriptionItemListingTest {

    @Test
    fun testMap() {
        val subscriptionItem = SubscriptionItem("My Subscription", 100L, "rxjava")

        val sub = TestSubscriber<Listing>()
        val s = Observable.just(subscriptionItem)
                .lift(OperatorMapSubscriptionItemListing())
                .subscribe(sub)
        s.unsubscribe()

        sub.assertNoErrors()
        sub.assertCompleted()

        val listing = sub.onNextEvents.single()

        assertEquals("My Subscription", listing.title)
        assertEquals("rxjava", listing.description)
        assertEquals("gdg://korea.android/listing/100?channelName=rxjava", listing.uri)
    }
}