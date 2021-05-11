package com.example.TheEconomist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.TheEconomist.Model.ResponseModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_headind.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HeadindFragment : BottomSheetDialogFragment() {

    private var headerList:MutableList<ResponseModel> = mutableListOf()
    private lateinit var bottomHeadingAdapter:BottomHeadingAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headind, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        bottomHeadingAdapter = BottomHeadingAdapter(headerList)
        h_rv.adapter = bottomHeadingAdapter
        h_rv.layoutManager = layoutManager
        callApi()
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
                    headerList.addAll(response.body()!!)
                    bottomHeadingAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<List<ResponseModel>>, t: Throwable) {
                Log.d("main", "onResponse: coming not")

            }

        })
    }

}