package com.example.TheEconomist.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun  addDetails(entity: MyEntity)

    @Query("SELECT * FROM details order by id DESC")
    fun getAllDetails(): LiveData<List<MyEntity>>

    @Query("DELETE FROM details WHERE id= :delId")
    fun deleteDetails(delId:Int)
}
//DELETE FROM tutorials_tbl WHERE tutorial_id=3