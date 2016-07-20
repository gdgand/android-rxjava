package com.androidhuman.example.rxjavainaction

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val btnViewPager by lazy {
        findViewById(R.id.btn_activity_main_view_pager) as Button
    }

    private val btnViewPagerFlipInterval by lazy {
        findViewById(R.id.btn_activity_main_view_pager_with_interval) as Button
    }

    private val btnViewPagerFlipIntervalDragHandling by lazy {
        findViewById(R.id.btn_activity_main_view_pager_interval_drag_handling) as Button
    }

    private val btnVisibilityEvent by lazy {
        findViewById(R.id.btn_activity_main_visibility_event) as Button
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnViewPager.setOnClickListener {
            startActivity(Intent(this@MainActivity, ViewPagerActivity::class.java))
        }

        btnViewPagerFlipInterval.setOnClickListener {
            startActivity(Intent(this@MainActivity, ViewPagerFlipIntervalActivity::class.java))
        }

        btnViewPagerFlipIntervalDragHandling.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                    ViewPagerFlipIntervalDragHandlingActivity::class.java))
        }

        btnVisibilityEvent.setOnClickListener {
            startActivity(Intent(this@MainActivity, VisibilityEventActivity::class.java))
        }
    }
}