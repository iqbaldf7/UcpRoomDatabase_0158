package com.example.ucp2_roomdatabase.data.dao

import androidx.room.Insert
import com.example.ucp2_roomdatabase.data.entity.Matakuliah

interface MatakuliahDao {
    @Insert
    suspend fun insertMstakuliah(matakuliah: Matakuliah)




}