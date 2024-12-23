package com.example.ucp2_roomdatabase.ui.view.dosen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_roomdatabase.entity.Dosen
import com.example.ucp2_roomdatabase.ui.costumwidget.CustomTopAppBar
import com.example.ucp2_roomdatabase.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.dosen.DetailDosenViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.dosen.DetailUiState
import com.example.ucp2_roomdatabase.ui.viewmodel.dosen.toDosenEntity

@Composable
fun DetailDosenView(
    modifier: Modifier = Modifier,
    viewModel: DetailDosenViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                judul = "Detail Dosen",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF212121), // Dark gray
                                Color(0xFF37474F)  // Dark blue-gray
                            )
                        )
                    )  // Dark gradient for the top bar
            )
        },
    ) { innerPadding ->
        val detailUiState by viewModel.detailUiState.collectAsState()
        BodyDetailDosen(
            modifier = Modifier.padding(innerPadding),
            detailUiState = detailUiState
        )
    }
}

@Composable
fun ItemDetailDosen(
    modifier: Modifier = Modifier,
    dosen: Dosen
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF303030), // Dark background for retro feel
            contentColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // NIP
            ComponentDetailDosen(judul = "NIP", isinya = dosen.nidn)
            Spacer(modifier = Modifier.height(8.dp))
            // Nama
            ComponentDetailDosen(judul = "Nama", isinya = dosen.nama)
            Spacer(modifier = Modifier.height(8.dp))
            // Jenis Kelamin
            ComponentDetailDosen(judul = "Jenis Kelamin", isinya = dosen.jenisKelamin)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun BodyDetailDosen(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState = DetailUiState()
) {
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailUiState.isUiEventNotEmpty -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailDosen(
                    dosen = detailUiState.detailUiEvent.toDosenEntity(),
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun ComponentDetailDosen(
    judul: String,
    isinya: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal, // Default font weight
            color = Color.Gray // Default color for title
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal, // Default font weight
            color = Color.White // White text for contrast on dark background
        )
    }
}
