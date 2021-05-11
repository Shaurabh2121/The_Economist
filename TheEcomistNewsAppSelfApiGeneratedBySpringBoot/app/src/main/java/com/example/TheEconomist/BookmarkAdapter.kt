package com.example.TheEconomist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.TheEconomist.Database.MyDatabase
import com.example.TheEconomist.Database.MyEntity
import kotlinx.android.synthetic.main.bookmark_item_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkAdapter(val responseList:List<MyEntity>):RecyclerView.Adapter<BookmarkAdapter.BookmarkHolder>() {

    class BookmarkHolder(view: View):RecyclerView.ViewHolder(view){
        var tvSubItemTitle: TextView = view.findViewById(R.id.tv_bookmark_title)
        var tvDescription: TextView =view.findViewById(R.id.tvBookmark_Des)
        var img_sub_item: ImageView =view.findViewById(R.id.img_bookmark_item)
        var img_save: ImageView =view.findViewById(R.id.btn_Save_bookmark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.bookmark_item_layout, parent, false)
        return BookmarkHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkHolder, position: Int) {
        val subItem= responseList[position]
        holder.tvSubItemTitle.text = subItem.title
        holder.tvDescription.text=subItem.author
        Glide.with(holder.img_sub_item).load(subItem.urlToImage).into(holder.img_sub_item)
        val database= MyDatabase.getDatabase(holder.itemView.context).getMyDao()
        holder.itemView.btn_Save_bookmark.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                database.deleteDetails(subItem.id)
                holder.itemView.btn_Save_bookmark.setImageResource(R.drawable.save_first)
            }

        }
    }

    override fun getItemCount(): Int {
       return responseList.size
    }
}