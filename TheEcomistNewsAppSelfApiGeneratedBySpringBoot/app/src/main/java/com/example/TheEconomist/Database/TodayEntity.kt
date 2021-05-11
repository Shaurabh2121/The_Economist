package com.example.TheEconomist.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "today_details")
data class TodayEntity(
    val title: String? = null,
    val author: String? = null,
    val briefDescription: String? = null,
    val description: String? = null,
    val urlToImage: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int=0
}