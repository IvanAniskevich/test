package com.example.testapp

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.Item
import com.example.testapp.databinding.SetingItemBinding

class SetingAdapter(val visibility: Visibility, val onTouchIcon: OnTouchIcon): RecyclerView.Adapter<SetingAdapter.SetingViewHolder>() {

    var setingList = ArrayList<Item>()

    class SetingViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = SetingItemBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.seting_item, parent, false)
        return SetingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SetingViewHolder, position: Int) {
       val item: Item = setingList[position]
        with(holder.binding){
            abbreviation.text = item.Cur_Abbreviation
            name.text = item.Cur_Name
            if (item.Visibility == true){
                switch1.isChecked = true
            }
            else switch1.isChecked = false
            switch1.setOnClickListener {
                if (switch1.isChecked) {
                    visibility.visibilityOn(item)
                } else {
                    visibility.visibilityOff(item)
                }
            }
            icon.setOnTouchListener { view, motionEvent ->
                onTouchIcon.onTouch(holder)
                true }


        }
    }

    override fun getItemCount(): Int {
        return setingList.size
    }

    fun setList(list: ArrayList<Item>){
        setingList= list
        notifyDataSetChanged()
    }
    interface Visibility{
        fun visibilityOn(item: Item)
        fun visibilityOff(item: Item)
    }
    interface OnTouchIcon {
        fun onTouch(viewHolder: SetingViewHolder)
    }

}