package com.example.TheEconomist

import com.example.TheEconomist.Model.ResponseModel
import com.example.TheEconomist.Model.ResponseTodayDTO
import com.example.TheEconomist.SearchModel.ResponseSearchDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("Api/WeeklyNews")
    fun getData():Call<List<ResponseModel>>

    @GET("Api/todayNews")
    fun getDataToday():Call<List<ResponseTodayDTO>>

    @GET("news")
   suspend fun getDataSearch(@Query("category") category:String):ResponseSearchDTO
}