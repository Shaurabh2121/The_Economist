package com.example.TheEconomist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TheEconomist.Model.DetailsDTO
import com.example.TheEconomist.Model.ResponseTodayDTO
import com.example.TheEconomist.R
import kotlinx.android.synthetic.main.today_header_item_layout.view.*

class TodayItemAdapter(private val itemList:List<ResponseTodayDTO>):RecyclerView.Adapter<TodayItemAdapter.ItemViewHolder>() {

    val viewPool = RecyclerView.RecycledViewPool()
    class ItemViewHolder(val view :View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.today_header_item_layout,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item:ResponseTodayDTO = itemList[position]
        holder.itemView.tv_item_header_today.text=item.header.toString()
        // Create layout manager with initial prefetch item count
        val layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.VERTICAL, false)
        layoutManager.initialPrefetchItemCount = item.details?.size!!

        // Create sub item view adapter
        val subItemAdapter = TodaySubItemAdapter(item.details as List<DetailsDTO>)
        holder.itemView.rv_sub_item_today.layoutManager=layoutManager
        holder.itemView.rv_sub_item_today.adapter = subItemAdapter
        holder.itemView.rv_sub_item_today.setRecycledViewPool(viewPool)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}