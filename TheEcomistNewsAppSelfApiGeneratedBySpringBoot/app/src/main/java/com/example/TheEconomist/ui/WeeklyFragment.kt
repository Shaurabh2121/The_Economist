package com.example.TheEconomist.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.TheEconomist.ApiClient
import com.example.TheEconomist.ItemAdapter
import com.example.TheEconomist.Model.ResponseModel
import com.example.TheEconomist.Network
import com.example.TheEconomist.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeeklyFragment : Fragment(R.layout.fragment_dashboard) {
    private var responseList:MutableList<ResponseModel> = mutableListOf()
    private lateinit var itemAdapter: ItemAdapter
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        itemAdapter = ItemAdapter(responseList)
        rv_item.adapter = itemAdapter
        rv_item.layoutManager = layoutManager
        callApi()
        CoroutineScope(Dispatchers.IO).launch {
            Btn_Flot.setOnClickListener {
                val action=WeeklyFragmentDirections.actionGlobalHeadindFragment()
                findNavController().navigate(action)
            }
        }


    }

    private fun callApi() {
        val apiClient= Network.getInstance().create(ApiClient::class.java)
        apiClient.getData().enqueue(object : Callback<List<ResponseModel>> {
            override fun onResponse(
                call: Call<List<ResponseModel>>,
                response: Response<List<ResponseModel>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("main", "onResponse: coming")
                    responseList.addAll(response.body()!!)
                    itemAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<List<ResponseModel>>, t: Throwable) {
                Log.d("main", "onResponse: coming not")

            }

        })
    }
}