package com.weijing.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weijing.R

/**
 * Created by kxf on 2017/11/1.
 */
class FourFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.four_layout, null)
        println(" FourFragment onCreateView:  $savedInstanceState")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println(" FourFragment onActivityCreated:  $savedInstanceState")

    }
}