package com.example.TheEconomist.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface TodayDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun  addDetailsToday(todayEntity: TodayEntity)

    @Query("SELECT * FROM today_details order by id DESC")
    fun getAllDetailsToday(): LiveData<List<TodayEntity>>

    @Query("DELETE FROM today_details WHERE id= :delId")
    fun deleteDetailsToday(delId:Int)


}