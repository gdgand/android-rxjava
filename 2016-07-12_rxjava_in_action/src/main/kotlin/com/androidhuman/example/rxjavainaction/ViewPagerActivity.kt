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
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var vpViewPager: ViewPager

    private lateinit var tvIndicator: TextView

    private val subscription by lazy { CompositeSubscription() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        vpViewPager = findViewById(R.id.vp_activity_view_pager) as ViewPager
        tvIndicator = findViewById(R.id.tv_activity_view_pager) as TextView

        vpViewPager.adapter = PageAdapter()
        subscription.add(RxViewPager.pageScrollStateChanges(vpViewPager)
                .startWith(ViewPager.SCROLL_STATE_IDLE)
                .retry()
                .map {
                    if (ViewPager.SCROLL_STATE_DRAGGING == it) {
                        "Dragging"
                    } else if (ViewPager.SCROLL_STATE_SETTLING == it) {
                        "Settling"
                    } else {
                        "Idle"
                    }
                }
                .onBackpressureBuffer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { tvIndicator.text = it })
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