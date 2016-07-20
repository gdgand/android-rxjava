package com.androidhuman.example.rxjavainaction.rx;

import com.androidhuman.example.rxjavainaction.error.ContentNotAvailableError;
import com.androidhuman.example.rxjavainaction.error.ContentRatingError;
import com.androidhuman.example.rxjavainaction.error.InsufficientBalanceError;
import com.androidhuman.example.rxjavainaction.model.Content;
import com.androidhuman.example.rxjavainaction.model.User;

import org.junit.Test;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func3;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;

public class RxContentTestJava {

    @Test
    public void testChecksAvailability() {
        Content content = new Content("test", 100, true, false);

        TestSubscriber<Void> sub = new TestSubscriber<>();
        Subscription s = RxContent.checksAvailability(content).subscribe(sub);
        s.unsubscribe();

        sub.assertNoErrors();
        sub.assertCompleted();
        sub.assertValue(null);
    }

    @Test
    public void testChecksAvailability_notInService() {
        Content content = new Content("test", 100, false, false);

        TestSubscriber<Void> sub = new TestSubscriber<>();
        Subscription s = RxContent.checksAvailability(content).subscribe(sub);
        s.unsubscribe();

        sub.assertError(ContentNotAvailableError.class);
    }

    @Test
    public void testChecksBalance() {
        Content content = new Content("test", 100, true, false);
        User user = new User("Foobar", true, 100);

        TestSubscriber<Void> sub = new TestSubscriber<>();
        Subscription s = RxContent.checksBalance(content, user).subscribe(sub);
        s.unsubscribe();

        sub.assertNoErrors();
        sub.assertCompleted();
        sub.assertValue(null);
    }

    @Test
    public void testChecksBalance_lowBalance() {
        Content content = new Content("test", 100, true, false);
        User user = new User("Foobar", true, 99);

        TestSubscriber<Void> sub = new TestSubscriber<>();
        Subscription s = RxContent.checksBalance(content, user).subscribe(sub);
        s.unsubscribe();

        sub.assertError(InsufficientBalanceError.class);
        //noinspection ThrowableResultOfMethodCallIgnored
        InsufficientBalanceError e = (InsufficientBalanceError) sub.getOnErrorEvents().get(0);
        assertEquals(1, e.getAmount());
    }

    @Test
    public void testChecksContentRating() {
        Content content = new Content("test", 100, true, true);
        User user = new User("Foobar", true, 100);

        TestSubscriber<Void> sub = new TestSubscriber<>();
        Subscription s = RxContent.checksContentRating(content, user).subscribe(sub);
        s.unsubscribe();

        sub.assertNoErrors();
        sub.assertCompleted();
        sub.assertValue(null);
    }

    @Test
    public void testChecksContentRating_forbidden() {
        Content content = new Content("test", 100, true, true);
        User user = new User("Foobar", false, 100);

        TestSubscriber<Void> sub = new TestSubscriber<>();
        Subscription s = RxContent.checksContentRating(content, user).subscribe(sub);
        s.unsubscribe();

        sub.assertError(ContentRatingError.class);
    }

    @Test
    public void testValidSequence() {
        Content content = new Content("test", 100, true, true);
        User user = new User("Foobar", true, 100);

        TestSubscriber<Void> sub = new TestSubscriber<>();
        Subscription s = Observable.zip(
                RxContent.checksAvailability(content),
                RxContent.checksBalance(content, user),
                RxContent.checksContentRating(content, user),
                new Func3<Void, Void, Void, Void>() {
                    @Override
                    public Void call(Void a, Void b, Void c) {
                        return null;
                    }
                }).subscribe(sub);
        s.unsubscribe();

        sub.assertNoErrors();
        sub.assertCompleted();
        sub.assertValue(null);
    }
}
