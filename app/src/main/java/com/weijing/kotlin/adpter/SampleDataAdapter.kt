package com.weijing.kotlin.adpter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.weijing.R
import com.weijing.kotlin.entity.SampleEntity
import com.weijing.kotlin.utils.SampleItemClick

/**
 * Created by kxf on 2017/11/2.
 */
class SampleDataAdapter(val list: MutableList<SampleEntity>, val listener: SampleItemClick) : RecyclerView.Adapter<SampleDataAdapter.SimpleRecyclerHolder>() {
    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: SimpleRecyclerHolder?, position: Int) {
        holder!!.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SimpleRecyclerHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.sample_item_layout, parent, false)
        return SimpleRecyclerHolder(view, listener)
    }

    class SimpleRecyclerHolder(itemView: View?, val itemClick: SampleItemClick) : RecyclerView.ViewHolder(itemView) {
        private val keyTxt: TextView
        private val valueTxt: TextView

        init {
            keyTxt = itemView!!.findViewById(R.id.sample_item_key) as TextView
            valueTxt = itemView!!.findViewById(R.id.sample_item_value) as TextView
        }

        fun bind(map: SampleEntity) {
            keyTxt.text = map.key
            valueTxt.text = map.value.toString()
            itemView.setOnClickListener { itemClick.click(map.id) }
        }
    }
}
