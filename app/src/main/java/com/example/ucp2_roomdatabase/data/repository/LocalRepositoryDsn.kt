package com.example.ucp2_roomdatabase.data.repository
import com.example.ucp2_roomdatabase.data.dao.DosenDao
import com.example.ucp2_roomdatabase.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDsn(
    private val dosenDao: DosenDao
) : RepositoryDosen {



    override suspend fun insertDosen(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }


    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override fun getDosen(nidn: String) : Flow<Dosen>{
        return dosenDao.getDosen(nidn)
    }
}