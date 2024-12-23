package com.example.ucp2_roomdatabase.dependenciesinjection

import android.content.Context
import com.example.ucp2_roomdatabase.data.database.KrsDatabase
import com.example.ucp2_roomdatabase.data.repository.LocalRepositoryDsn
import com.example.ucp2_roomdatabase.data.repository.LocalRepositoryMk
import com.example.ucp2_roomdatabase.data.repository.RepositoryDosen
import com.example.ucp2_roomdatabase.data.repository.RepositoryMataKuliah

interface InterfaceContainerApp {
    val RepositoryDosen: RepositoryDosen
    val RepositoryMataKuliah: RepositoryMataKuliah

}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val RepositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDsn(KrsDatabase.getDatabase(context).DosenDao())
    }

    override val RepositoryMataKuliah: RepositoryMataKuliah by lazy {
        LocalRepositoryMk(KrsDatabase.getDatabase(context).mataKuliahDao())
    }
}
