package com.example.TheEconomist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.TheEconomist.Database.MyDatabase
import com.example.TheEconomist.Database.TodayEntity
import com.example.TheEconomist.R
import kotlinx.android.synthetic.main.today_saved_item_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodaySavedAdapter(private val itemList:List<TodayEntity>):RecyclerView.Adapter<TodaySavedAdapter.TodayViewHolder>() {
    class TodayViewHolder(val view:View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayViewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.today_saved_item_layout,parent,false)
        return TodayViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodayViewHolder, position: Int) {
        val item:TodayEntity=itemList[position]
        holder.itemView.today_save_title.text=item.title
        holder.itemView.today_save_Des.text=item.description
        Glide.with(holder.itemView.today_save_image).load(item.urlToImage).into(holder.itemView.today_save_image)


        val database= MyDatabase.getDatabase(holder.itemView.context).getMyTodayDao()
        holder.itemView.today_btn_Save_bookmark.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                database.deleteDetailsToday(item.id)
                holder.itemView.today_btn_Save_bookmark.setImageResource(R.drawable.save_first)
            }

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}