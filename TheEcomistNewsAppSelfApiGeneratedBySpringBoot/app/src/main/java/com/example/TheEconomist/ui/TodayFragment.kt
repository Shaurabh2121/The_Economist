package com.example.TheEconomist.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.TheEconomist.Adapter.TodayItemAdapter
import com.example.TheEconomist.ApiClient
import com.example.TheEconomist.Model.ResponseTodayDTO
import com.example.TheEconomist.Network
import com.example.TheEconomist.R
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodayFragment : Fragment(R.layout.fragment_home) {
    private var responseList:MutableList<ResponseTodayDTO> = mutableListOf()
    private lateinit var itemAdapter: TodayItemAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        itemAdapter = TodayItemAdapter(responseList)
        rv_today.adapter = itemAdapter
        rv_today.layoutManager = layoutManager
        callApi()
    }


    private fun callApi() {
        val apiClient= Network.getInstance().create(ApiClient::class.java)
        apiClient.getDataToday().enqueue(object : Callback<List<ResponseTodayDTO>> {
            override fun onResponse(
                    call: Call<List<ResponseTodayDTO>>,
                    response: Response<List<ResponseTodayDTO>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("main", "onResponse: coming")
                    responseList.addAll(response.body()!!)
                    itemAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<List<ResponseTodayDTO>>, t: Throwable) {
                Log.d("main", "onResponse: coming not")

            }

        })
    }

}