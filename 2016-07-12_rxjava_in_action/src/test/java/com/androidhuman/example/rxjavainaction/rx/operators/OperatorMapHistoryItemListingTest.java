package com.androidhuman.example.rxjavainaction.rx.operators;

import com.androidhuman.example.rxjavainaction.model.HistoryItem;
import com.androidhuman.example.rxjavainaction.model.Listing;

import org.junit.Test;

import rx.Observable;
import rx.Subscription;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

public class OperatorMapHistoryItemListingTest {

    @Test
    public void testMap() {
        HistoryItem historyItem = new HistoryItem("My History", 100L);

        TestSubscriber<Listing> sub = new TestSubscriber<>();
        Subscription s = Observable.just(historyItem)
                .lift(new OperatorMapHistoryItemListing())
                .subscribe(sub);
        s.unsubscribe();

        sub.assertNoErrors();
        sub.assertCompleted();

        Listing listing = sub.getOnNextEvents().get(0);

        assertEquals("My History", listing.getTitle());
        assertEquals("gdg://korea.android/listing/100", listing.getUri());
    }
}
