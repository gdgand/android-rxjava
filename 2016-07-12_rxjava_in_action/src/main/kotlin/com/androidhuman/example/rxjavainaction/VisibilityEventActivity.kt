package com.androidhuman.example.rxjavainaction

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxrelay.PublishRelay
import kotlinx.android.synthetic.main.activity_visibility_event.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

class VisibilityEventActivity : AppCompatActivity() {

    private val visibilityPublishRelay by lazy { PublishRelay.create<Int>() }

    private val subscription by lazy { CompositeSubscription() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visibility_event)

        with(subscription) {
            add(visibilityPublishRelay
                    .retry()
                    .distinctUntilChanged()
                    .onBackpressureBuffer()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        changeVisibility(it)
                    })

            add(RxView.clicks(btn_activity_visibility_event)
                    .concatMap {
                        Observable.concat(
                                Observable.just(View.VISIBLE),
                                Observable.just(View.GONE).delay(3, TimeUnit.SECONDS))
                    }
                    .subscribe(visibilityPublishRelay))
        }
    }

    private fun changeVisibility(toVisibility: Int) {
        with(tv_activity_visibility_event) {
            if (toVisibility == visibility) {
                return
            }
            visibility = toVisibility
        }
    }
}