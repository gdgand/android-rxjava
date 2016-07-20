package com.androidhuman.example.rxjavainaction

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.androidhuman.example.rxjavainaction.rxbinding.support.v4.view.RxViewPager

import kotlinx.android.synthetic.main.activity_view_pager.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

class ViewPagerFlipIntervalDragHandlingActivity : AppCompatActivity() {

    private val subscription by lazy { CompositeSubscription() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        vp_activity_view_pager.adapter = PageAdapter()

        val timer = Observable.interval(1000, TimeUnit.MILLISECONDS)

        val dragging = RxViewPager.pageScrollStateChanges(vp_activity_view_pager)
                .map { ViewPager.SCROLL_STATE_DRAGGING == it }
                .distinctUntilChanged()
                .startWith(false)

        subscription.add(timer.withLatestFrom(dragging, { timer, dragging -> dragging })
                .filter { !it }
                .retry()
                .onBackpressureDrop()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    with(vp_activity_view_pager) {
                        val currentIdx = currentItem
                        currentItem = if (currentIdx == adapter.count - 1) 0 else currentItem + 1
                    }
                })
    }

    override fun onStop() {
        super.onStop()
        if (isFinishing) {
            clearSubscription()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearSubscription()
    }

    private fun clearSubscription() {
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
    }

    inner class PageAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = TextView(container.context).apply {
                layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

                gravity = Gravity.CENTER
                text = "Page $position"
                setBackgroundColor(Color.GREEN)
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            }

            container.addView(view)

            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int {
            return 10
        }

        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            return view == `object`
        }
    }
}