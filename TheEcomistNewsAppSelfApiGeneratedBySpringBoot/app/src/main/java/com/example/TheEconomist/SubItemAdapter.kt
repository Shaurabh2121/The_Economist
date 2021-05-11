 package com.example.TheEconomist

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.TheEconomist.Database.MyDatabase
import com.example.TheEconomist.Database.MyEntity
import com.example.TheEconomist.Model.DetailsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


  class SubItemAdapter (val subItemList: List<DetailsModel>) : RecyclerView.Adapter<SubItemAdapter.SubItemViewHolder>() {
      companion object {
          var count = 0
      }

     override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SubItemViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.details_item_layout, viewGroup, false)
        return SubItemViewHolder(view)
    }

    override fun onBindViewHolder(subItemViewHolder: SubItemViewHolder, position: Int) {

        val subItem: DetailsModel = subItemList[position]
        subItemViewHolder.tvSubItemTitle.text = subItem.title
        subItemViewHolder.tvDescription.text=subItem.author
        Glide.with(subItemViewHolder.img_sub_item).load(subItem.urlToImage).into(subItemViewHolder.img_sub_item)

        val database=MyDatabase.getDatabase(subItemViewHolder.itemView.context).getMyDao()
        subItemViewHolder.img_save.setOnClickListener {
            if (count==0){
                count++
                Log.d("main", "onBindViewHolder: "+ count)
                CoroutineScope(Dispatchers.IO).launch {
                    database.addDetails(MyEntity(subItem.title,subItem.author,subItem.description,subItem.urlToImage))
                    subItemViewHolder.img_save.setImageResource(R.drawable.save_second)
                }
            }else if(count==1){
                count--
                Log.d("main", "onBindViewHolder: "+ count)
                CoroutineScope(Dispatchers.IO).launch {
                    database.deleteDetails(subItem.id!!)
                    subItemViewHolder.img_save.setImageResource(R.drawable.save_first)
                }
            }
        }


        subItemViewHolder.img_sub_item.setOnClickListener {
            val intent=Intent(subItemViewHolder.itemView.context,DescriptionActivity::class.java)
            intent.putExtra("description",subItem.description)
            intent.putExtra("pic",subItem.urlToImage)
            intent.putExtra("title",subItem.title)
            subItemViewHolder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return subItemList.size
    }

     class SubItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSubItemTitle: TextView= itemView.findViewById(R.id.tv_sub_item_title)
        var tvDescription: TextView=itemView.findViewById(R.id.tvDescription)
        var img_sub_item:ImageView=itemView.findViewById(R.id.img_sub_item)
        var img_save:ImageView=itemView.findViewById(R.id.btn_Save)


    }

}