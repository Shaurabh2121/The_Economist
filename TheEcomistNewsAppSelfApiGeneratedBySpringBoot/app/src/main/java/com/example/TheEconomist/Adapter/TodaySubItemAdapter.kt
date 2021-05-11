package com.example.TheEconomist.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.TheEconomist.Database.MyDatabase
import com.example.TheEconomist.Database.TodayEntity
import com.example.TheEconomist.Model.DetailsDTO
import com.example.TheEconomist.R
import com.example.TheEconomist.TodayDescriptionActivity
import kotlinx.android.synthetic.main.today_details_item_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodaySubItemAdapter(private val subItemList:List<DetailsDTO>):RecyclerView.Adapter<TodaySubItemAdapter.TodaySubItemViewHolder>() {

    companion object {
        var count1 = 0
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodaySubItemViewHolder {
      val view =LayoutInflater.from(parent.context).inflate(R.layout.today_details_item_layout,parent,false)
        return TodaySubItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodaySubItemViewHolder, position: Int) {
        val subItem: DetailsDTO = subItemList[position]
        holder.itemView.tvTitle_today.text = subItem.title
        holder.itemView.tv_author_today.text=subItem.author
        holder.itemView.tvDescription_today.text=subItem.description
        Glide.with(holder.itemView.item_img_today).load(subItem.urlToImage).into(holder.itemView.item_img_today)


        val database= MyDatabase.getDatabase(holder.itemView.context).getMyTodayDao()
        holder.itemView.btn_bookmark_today_item.setOnClickListener {
            if (count1 ==0){
                count1++
                Log.d("main", "onBindViewHolder: "+ count1)
                CoroutineScope(Dispatchers.IO).launch {
                    database.addDetailsToday(TodayEntity(subItem.title,subItem.author,subItem.briefDescription,subItem.description,subItem.urlToImage))
                    holder.itemView.btn_bookmark_today_item.setImageResource(R.drawable.save_second)
                }
            }else if(count1 ==1){
                count1--
                Log.d("main", "onBindViewHolder: "+ count1)
                CoroutineScope(Dispatchers.IO).launch {
                    database.deleteDetailsToday(subItem.id!!)
                    holder.itemView.btn_bookmark_today_item.setImageResource(R.drawable.save_first)
                }
            }
        }


        holder.itemView.item_img_today.setOnClickListener {
            val intent= Intent(holder.itemView.context, TodayDescriptionActivity::class.java)
            intent.putExtra("description",subItem.description)
            intent.putExtra("briefDescription",subItem.briefDescription)
            intent.putExtra("pic",subItem.urlToImage)
            intent.putExtra("title",subItem.title)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
     return subItemList.size
    }


    class TodaySubItemViewHolder(val view: View):RecyclerView.ViewHolder(view){

    }
}