package com.example.TheEconomist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.TheEconomist.SearchModel.DataSearchDTO
import kotlinx.coroutines.Dispatchers

class MyViewModel:ViewModel() {
    val repository=MyRepository()

    fun getData(data:String): LiveData<List<DataSearchDTO>> {
        return liveData (Dispatchers.IO) {
            val result = repository.getData(data).data
            emit(result?.data!! as List<DataSearchDTO>)
        }
    }
}