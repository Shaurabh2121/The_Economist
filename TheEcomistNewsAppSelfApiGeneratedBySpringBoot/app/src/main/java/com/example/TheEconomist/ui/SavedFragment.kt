package com.example.TheEconomist.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.example.TheEconomist.Adapter.TodaySavedAdapter
import com.example.TheEconomist.BookmarkAdapter
import com.example.TheEconomist.Database.MyDatabase
import com.example.TheEconomist.Database.MyEntity
import com.example.TheEconomist.Database.TodayEntity
import com.example.TheEconomist.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class SavedFragment  : Fragment(R.layout.fragment_notifications) {
    private var todaySavedList:MutableList<TodayEntity> = mutableListOf()

    private var headerList:MutableList<MyEntity> = mutableListOf()

    private lateinit var bookmarkAdapter: BookmarkAdapter
    private lateinit var todaySavedAdapter: TodaySavedAdapter
    private lateinit var mergeAdapter:MergeAdapter
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        bookmarkAdapter = BookmarkAdapter(headerList)
        todaySavedAdapter=TodaySavedAdapter(todaySavedList)

        mergeAdapter = MergeAdapter(bookmarkAdapter, todaySavedAdapter)
        rv_bookmark_item.adapter = mergeAdapter
        todaySavedList.clear()
        headerList.clear()
        mergeAdapter.notifyDataSetChanged()
        rv_bookmark_item.layoutManager = layoutManager
        callViewModel()
        callViewModelToday()




    }

    private fun callViewModelToday() {
        context?.let { s->
            val database = MyDatabase.getDatabase(s).getMyTodayDao()
            database.getAllDetailsToday().observe(viewLifecycleOwner, Observer {
                todaySavedList.clear()
                todaySavedList.addAll(it)
                todaySavedAdapter.notifyDataSetChanged()
            })
        }
    }

    private fun callViewModel() {
        context?.let {
            val database= MyDatabase.getDatabase(it).getMyDao()
            database.getAllDetails().observe(viewLifecycleOwner, Observer {
                headerList.clear()
                headerList.addAll(it)
                bookmarkAdapter.notifyDataSetChanged()
            })

        }
    }


}

