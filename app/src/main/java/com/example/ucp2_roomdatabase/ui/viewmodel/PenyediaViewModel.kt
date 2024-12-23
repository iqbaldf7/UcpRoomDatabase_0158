package com.example.ucp2_roomdatabase.ui.viewmodel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2_roomdatabase.KrsApp
import com.example.ucp2_roomdatabase.data.repository.LocalRepositoryDsn
import com.example.ucp2_roomdatabase.data.repository.LocalRepositoryMk
import com.example.ucp2_roomdatabase.ui.viewmodel.dosen.DetailDosenViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.dosen.DosenViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.dosen.HomeDosenViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.matkul.DetailMataKuliahViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.matkul.HomeMataKuliahViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.matkul.MataKuliahViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.matkul.UpdateMataKuliahViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                KrsApp().containerApp.RepositoryDosen as LocalRepositoryDsn
            )
        }
        initializer {
            HomeDosenViewModel(
                KrsApp().containerApp.RepositoryDosen
            )
        }
        initializer {
            DetailDosenViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.RepositoryDosen
            )
        }
        initializer {
            MataKuliahViewModel(
                KrsApp().containerApp.RepositoryMataKuliah,
                KrsApp().containerApp.RepositoryDosen
            )
        }
        initializer {
            HomeMataKuliahViewModel(
                KrsApp().containerApp.RepositoryMataKuliah,
            )
        }
        initializer {
            DetailMataKuliahViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.RepositoryMataKuliah,
            )
        }
        initializer {
            UpdateMataKuliahViewModel(
                createSavedStateHandle(),
                KrsApp().containerApp.RepositoryMataKuliah as LocalRepositoryMk,
                KrsApp().containerApp.RepositoryDosen as LocalRepositoryDsn,
            )
        }
    }
}



fun CreationExtras.KrsApp() : KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)
