package com.example.ucp2_roomdatabase.data.database

import android.content.Context
import androidx.room.Database
import com.example.ucp2_roomdatabase.data.dao.MatakuliahDao
import com.example.ucp2_roomdatabase.data.entity.Matakuliah

@Database(entities = [Matakuliah::class], version = 1, exportSchema = false)
abstract class SksDatabase : RoomDatabase() {
    abstract fun  matakuliahDao(): MatakuliahDao
    companion object {
        @Volatile
        private var Instance: SksDatabase? = null

        fun getDatabase(context: Context): SksDatabase {
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    SksDatabase::class.java,
                    "SksDatabase"
                )
                    .build().also { Instance= it }
            })
        }
    }
}