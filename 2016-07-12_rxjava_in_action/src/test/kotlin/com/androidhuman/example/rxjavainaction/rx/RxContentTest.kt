package com.androidhuman.example.rxjavainaction.rx

import com.androidhuman.example.rxjavainaction.error.ContentNotAvailableError
import com.androidhuman.example.rxjavainaction.error.ContentRatingError
import com.androidhuman.example.rxjavainaction.error.InsufficientBalanceError
import com.androidhuman.example.rxjavainaction.model.Content
import com.androidhuman.example.rxjavainaction.model.User
import org.junit.Assert.assertEquals
import org.junit.Test
import rx.Observable
import rx.observers.TestSubscriber

class RxContentTest {

    @Test
    fun testChecksAvailability() {
        val content = Content("test", 100, true, false)

        val sub = TestSubscriber<Void>()
        val s = content.checksAvailability().subscribe(sub)
        s.unsubscribe()

        sub.assertNoErrors()
        sub.assertCompleted()
        sub.assertValue(null)
    }

    @Test
    fun testChecksAvailability_notInService() {
        val content = Content("test", 100, false, false)

        val sub = TestSubscriber<Void>()
        val s = content.checksAvailability().subscribe(sub)
        s.unsubscribe()

        sub.assertError(ContentNotAvailableError::class.java)
    }

    @Test
    fun testChecksBalance() {
        val content = Content("test", 100, true, false)
        val user = User("Foobar", true, 100)

        val sub = TestSubscriber<Void>()
        val s = content.checksBalance(user).subscribe(sub)
        s.unsubscribe()

        sub.assertNoErrors()
        sub.assertCompleted()
        sub.assertValue(null)
    }

    @Test
    fun testChecksBalance_lowBalance() {
        val content = Content("test", 100, true, false)
        val user = User("Foobar", true, 99)

        val sub = TestSubscriber<Void>()
        val s = content.checksBalance(user).subscribe(sub)
        s.unsubscribe()

        sub.assertError(InsufficientBalanceError::class.java)
        with(sub.onErrorEvents.single() as InsufficientBalanceError) {
            assertEquals(1, amount)
        }
    }

    @Test
    fun testChecksContentRating() {
        val content = Content("test", 100, true, true)
        val user = User("Foobar", true, 100)

        val sub = TestSubscriber<Void>()
        val s = content.checksContentRating(user).subscribe(sub)
        s.unsubscribe()

        sub.assertNoErrors()
        sub.assertCompleted()
        sub.assertValue(null)
    }

    @Test
    fun testChecksContentRating_forbidden() {
        val content = Content("test", 100, true, true)
        val user = User("Foobar", false, 100)

        val sub = TestSubscriber<Void>()
        val s = content.checksContentRating(user).subscribe(sub)
        s.unsubscribe()

        sub.assertError(ContentRatingError::class.java)
    }

    @Test
    fun testValidSequence() {
        val content = Content("test", 100, true, true)
        val user = User("Foobar", true, 100)

        val sub = TestSubscriber<Void>()
        val s = with(content) {
            Observable.zip(
                    checksAvailability(), checksBalance(user), checksContentRating(user),
                    { a, b, c -> null })
        }.subscribe(sub)
        s.unsubscribe()

        sub.assertNoErrors()
        sub.assertCompleted()
        sub.assertValue(null)
    }
}