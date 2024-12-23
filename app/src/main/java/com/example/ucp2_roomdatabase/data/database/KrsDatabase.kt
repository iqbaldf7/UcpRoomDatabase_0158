package com.example.ucp2_roomdatabase.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2_roomdatabase.data.dao.DosenDao
import com.example.ucp2_roomdatabase.data.dao.MataKuliahDao
import com.example.ucp2_roomdatabase.data.entity.MataKuliah
import com.example.ucp2_roomdatabase.entity.Dosen

@Database(entities = [Dosen::class, MataKuliah::class], version = 1, exportSchema = false)
abstract class KrsDatabase : RoomDatabase() {
    abstract fun mataKuliahDao(): MataKuliahDao
    abstract fun DosenDao(): DosenDao

    companion object {
        @Volatile
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: android.content.Context): KrsDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabase::class.java,
                    "KrsDatabase"
                )
                    .build().also { Instance = it }
            }
        }
    }
}