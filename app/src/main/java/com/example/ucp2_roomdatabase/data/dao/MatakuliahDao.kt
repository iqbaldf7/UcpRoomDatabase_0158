package com.example.ucp2_roomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2_roomdatabase.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface MataKuliahDao {
    @Insert
    suspend fun insertMataKuliah(matakuliah: MataKuliah)

    @Query("SELECT * FROM matakuliah")
    fun getAllMataKuliah(): Flow<List<MataKuliah>>

    @Update
    suspend fun updateMataKuliah(matakuliah: MataKuliah)

    @Delete
    suspend fun deleteMataKuliah(matakuliah: MataKuliah)

    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getDetailMataKuliah(kode: String): Flow<MataKuliah>
}

