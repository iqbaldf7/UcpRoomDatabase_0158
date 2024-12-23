package com.example.ucp2_roomdatabase.data.repository
import com.example.ucp2_roomdatabase.data.dao.MataKuliahDao
import com.example.ucp2_roomdatabase.data.entity.MataKuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMk(
    private val mataKuliahDao: MataKuliahDao
) : RepositoryMataKuliah{


    // Menambahkan mata kuliah baru
    override suspend fun insertMataKuliah(matakuliah: MataKuliah) {
        mataKuliahDao.insertMataKuliah(matakuliah)
    }

    // Mendapatkan semua mata kuliah
    override fun getAllMataKuliah(): Flow<List<MataKuliah>> {
        return mataKuliahDao.getAllMataKuliah()
    }

    // Mengupdate mata kuliah
    override suspend fun updateMataKuliah(matakuliah: MataKuliah) {
        mataKuliahDao.updateMataKuliah(matakuliah)
    }

    // Menghapus mata kuliah
    override suspend fun deleteMataKuliah(matakuliah: MataKuliah) {
        mataKuliahDao.deleteMataKuliah(matakuliah)
    }

    // Mendapatkan detail mata kuliah berdasarkan kode
    override fun getDetailMataKuliah(kode: String): Flow<MataKuliah> {
        return mataKuliahDao.getDetailMataKuliah(kode)
    }
}
