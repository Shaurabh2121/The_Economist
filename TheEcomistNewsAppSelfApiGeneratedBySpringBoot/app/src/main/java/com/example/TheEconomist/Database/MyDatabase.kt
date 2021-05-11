package com.example.TheEconomist.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [MyEntity::class,TodayEntity::class],version = 3,exportSchema = false)
abstract class MyDatabase():RoomDatabase() {

    abstract fun getMyDao():MyDao
    abstract fun getMyTodayDao():TodayDao
    companion object{
        private var INSTANCE:MyDatabase?=null
        fun getDatabase(context: Context):MyDatabase{
            if(INSTANCE==null){
                val builder: Builder<MyDatabase> = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "details_database"
                )
                builder.fallbackToDestructiveMigration()
                return builder.build()
                return INSTANCE!!
            }else{
                return INSTANCE!!
            }
        }
    }
}