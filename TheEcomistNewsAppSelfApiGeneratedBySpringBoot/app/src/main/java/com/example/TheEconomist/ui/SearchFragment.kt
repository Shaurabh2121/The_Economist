package com.example.TheEconomist.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.TheEconomist.*
import com.example.TheEconomist.Adapter.SearchAdapter
import com.example.TheEconomist.SearchModel.DataSearchDTO
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(R.layout.fragment_search) {

    private var responseList:MutableList<DataSearchDTO> = mutableListOf()
    private lateinit var itemAdapter: SearchAdapter
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        itemAdapter = SearchAdapter(responseList)
        recyclerView_search.adapter = itemAdapter
        recyclerView_search.layoutManager = layoutManager
        btnSearch.setOnClickListener {
            callViewModel()
        }

    }


    private fun callViewModel() {

        val viewModel=ViewModelProviders.of(this).get(MyViewModel::class.java)
        viewModel.getData(et_search.text.toString()).observe(viewLifecycleOwner, Observer {
               if (it.isNotEmpty()){
                   recyclerView_search.visibility=View.VISIBLE
                   pic_search_vis.visibility=View.GONE
                   responseList.clear()
                   responseList.addAll(it)
                  Log.d("main", "callViewModel: "+it[0].content)
            itemAdapter.notifyDataSetChanged()
               }
        })
    }
}