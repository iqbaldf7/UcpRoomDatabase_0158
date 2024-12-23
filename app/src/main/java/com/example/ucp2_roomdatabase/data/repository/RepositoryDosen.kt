package com.example.ucp2_roomdatabase.data.repository
import com.example.ucp2_roomdatabase.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDosen {
    suspend fun insertDosen(dosen: Dosen)

    fun getAllDosen(): Flow<List<Dosen>>

    fun getDosen(nidn: String) : Flow<Dosen>
}

