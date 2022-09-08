package com.example.testapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.Item
import com.example.testapp.data.RedyItem
import com.example.testapp.databinding.ListItemBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {



    var listItems = ArrayList<Item>()

    class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {
      val binding = ListItemBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item: Item = listItems[position]

        if (item.Visibility == true) {
            holder.itemView.visibility = View.VISIBLE
        }
        else holder.itemView.visibility = View.GONE

        with(holder.binding) {
            abbreviation.text = item.Cur_Abbreviation
            name.text = item.Cur_Name
            officialRateToday.text = item.Cur_OfficialRate.toString()
            textView3.text = item.Cur_OfficialRate.toString()
        }


    }

    override fun getItemCount(): Int = listItems.size

    fun setList(list: ArrayList<Item>){
        listItems= list
        notifyDataSetChanged()
    }
}