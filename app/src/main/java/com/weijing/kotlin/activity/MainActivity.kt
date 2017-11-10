package com.weijing.kotlin.activity

import android.graphics.drawable.Drawable
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.view.KeyEvent
import android.view.View
import android.widget.Toast

import com.weijing.R
import com.weijing.kotlin.fragment.FourFragment
import com.weijing.kotlin.fragment.OneFragment
import com.weijing.kotlin.fragment.ThreeFragment
import com.weijing.kotlin.fragment.TwoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity(), View.OnClickListener {

    private var receivableF: OneFragment? = null
    private var orderF: TwoFragment? = null
    private var duiZhangF: ThreeFragment? = null
    private var myF: FourFragment? = null
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null
    override fun onClick(p: View?) {
        when (p!!.id) {
            R.id.main_fragment_caishi ->
                currentFrame(1)
            R.id.main_fragment_my ->
                currentFrame(2)
            R.id.main_fragment_order ->
                currentFrame(3)
            R.id.main_fragment_recon ->
                currentFrame(4)
        }
        fragmentTransaction!!.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager
        main_fragment_caishi.setOnClickListener(this)
        main_fragment_my.setOnClickListener(this)
        main_fragment_order.setOnClickListener(this)
        main_fragment_recon.setOnClickListener(this)
    }

    private fun clear() {
        var draw: Drawable = ContextCompat.getDrawable(this, R.mipmap.my_no)
        // main_fragment_caishi.setTextColor(R.color.qianhui)
        main_fragment_caishi.setCompoundDrawablesWithIntrinsicBounds(null, draw, null, null)
        draw = ContextCompat.getDrawable(this, R.mipmap.dz_no)
        main_fragment_recon.setCompoundDrawablesWithIntrinsicBounds(null, draw, null, null)
        draw = ContextCompat.getDrawable(this, R.mipmap.dd_no)
        main_fragment_order.setCompoundDrawablesWithIntrinsicBounds(null, draw, null, null)
        draw = ContextCompat.getDrawable(this, R.mipmap.my_no)
        main_fragment_my.setCompoundDrawablesWithIntrinsicBounds(null, draw, null, null)
    }
    private fun currentFrame(i: Int) {
        if (fragmentTransaction == null)
            fragmentTransaction = fragmentManager!!.beginTransaction()
        clear()
        var draw: Drawable
        when (i) {
            1 -> {
                if (receivableF == null)
                    receivableF = OneFragment()
                println("11")
                draw = ContextCompat.getDrawable(this, R.mipmap.sk_se)
                fragmentTransaction!!.replace(R.id.main_frame, receivableF)
                main_fragment_caishi.setCompoundDrawablesWithIntrinsicBounds(null, draw, null, null)
            }
            2 -> {
                if (myF == null)
                    myF = FourFragment()
                println("22")
                draw = ContextCompat.getDrawable(this, R.mipmap.my_se)
                fragmentTransaction!!.replace(R.id.main_frame, myF)
                main_fragment_my.setCompoundDrawablesWithIntrinsicBounds(null, draw, null, null)
            }
            3 -> {
                if (orderF == null)
                    orderF = TwoFragment()
                println("33")
                draw = ContextCompat.getDrawable(this, R.mipmap.dd_se)
                fragmentTransaction!!.replace(R.id.main_frame, orderF)
                main_fragment_order.setCompoundDrawablesWithIntrinsicBounds(null, draw, null, null)
            }
            4 -> {
                if (duiZhangF == null)
                    duiZhangF = ThreeFragment()
                println("44")
                draw = ContextCompat.getDrawable(this, R.mipmap.dz_se)
                fragmentTransaction!!.replace(R.id.main_frame, duiZhangF)
                main_fragment_recon.setCompoundDrawablesWithIntrinsicBounds(null, draw, null, null)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        receivableF = null
        orderF = null
        duiZhangF = null
        myF = null
        fragmentManager = null
        System.gc()
    }

    var exitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event!!.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(applicationContext, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                System.exit(0);
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
