package com.example.TheEconomist

import com.example.TheEconomist.SearchModel.ResponseSearchDTO
import com.example.TheEconomist.network.Resource
import com.example.TheEconomist.network.ResponseHandler
import com.example.TheEconomist.network.RetrofitGenerater

class MyRepository {
    val api: ApiClient = RetrofitGenerater.getInstance().create(ApiClient::class.java)
    val responseHandler = ResponseHandler()

    suspend fun getData(data:String): Resource<ResponseSearchDTO> {
        val result = api.getDataSearch(data)
        return responseHandler.handleSuccess(result)
    }


}