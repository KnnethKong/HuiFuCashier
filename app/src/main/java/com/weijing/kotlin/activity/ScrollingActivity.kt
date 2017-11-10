package com.weijing.kotlin.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.weijing.R
import com.weijing.kotlin.utils.Preference

class ScrollingActivity : AppCompatActivity() {
    private var storeId: Long by Preference<Long>(this, "storeId", 0)
    private var token: String by Preference<String>(this, "token", "")
    private var userId: Long by Preference<Long>(this, "userId", 0)
    private var userType: Int by Preference<Int>(this, "userType", 6)
    private var merchantId: Long by Preference<Long>(this, "merchantId", 0)
    private var storeName: String by Preference<String>(this, "storeName", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.title = storeName
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { view -> finish() }
        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show() }
    }
}
