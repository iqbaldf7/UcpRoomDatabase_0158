package com.example.ucp2_roomdatabase.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matakuliah")
data class Matakuliah(
    @PrimaryKey
    val id: Int = 0,
    val kodeMatakuliah: String,
    val namaMatakuliah: String,
    val sks: Int
)