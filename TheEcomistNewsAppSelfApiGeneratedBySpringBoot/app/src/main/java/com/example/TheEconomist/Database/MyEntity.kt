package com.example.TheEconomist.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class MyEntity(
        val title: String? = null,
        val author: String? = null,
        val description: String? = null,
        val urlToImage: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int=0
}