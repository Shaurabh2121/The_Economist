package com.example.TheEconomist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.TheEconomist.R
import com.example.TheEconomist.SearchModel.DataSearchDTO
import kotlinx.android.synthetic.main.search_item_layout.view.*

class SearchAdapter(private val searchList:List<DataSearchDTO>):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(val view: View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item_layout,parent,false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {


        val searchItem= searchList[position]


            holder.itemView.tvTitle_search.text= searchItem.title
            holder.itemView.tv_time_search.text= searchItem.date
            holder.itemView.tv_content_search.text= searchItem.content
            Glide.with(holder.itemView.ivImage_search).load(searchItem.imageUrl).into(holder.itemView.ivImage_search)


    }

    override fun getItemCount(): Int {
     return searchList.size
    }
}