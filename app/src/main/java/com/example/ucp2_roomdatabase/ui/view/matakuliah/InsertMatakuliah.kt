package com.example.ucp2_roomdatabase.ui.view.matakuliah

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_roomdatabase.entity.Dosen
import com.example.ucp2_roomdatabase.ui.Navigation.AlamatNavigasi
import com.example.ucp2_roomdatabase.ui.costumwidget.CustomTopAppBar
import com.example.ucp2_roomdatabase.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.matkul.FormErrorState
import com.example.ucp2_roomdatabase.ui.viewmodel.matkul.MataKuliahEvent
import com.example.ucp2_roomdatabase.ui.viewmodel.matkul.MataKuliahUiState
import com.example.ucp2_roomdatabase.ui.viewmodel.matkul.MataKuliahViewModel
import kotlinx.coroutines.launch

object DestinasiInsertMataKuliah : AlamatNavigasi {
    override val route: String = "insert_mk"
}

@Composable
fun InsertMataKuliahView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MataKuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState // Ambil UI state dari ViewModel
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Observasi perubahan snackbarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White) // Mengubah latar belakang menjadi putih
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CustomTopAppBar(
                    onBack = onBack,
                    showBackButton = true,
                    judul = "Tambah Mata Kuliah"
                )

                // Isi Body
                InsertBodyMataKuliah(
                    uiState = uiState,
                    dosenList = uiState.dosentList,
                    onValueChange = { updateEvent -> viewModel.updateState(updateEvent) },
                    onClick = {
                        coroutineScope.launch {
                            viewModel.saveData() // Simpan data
                        }
                        onNavigate()
                    }
                )
            }
        }
    }
}

@Composable
fun InsertBodyMataKuliah(
    modifier: Modifier = Modifier,
    onValueChange: (MataKuliahEvent) -> Unit,
    uiState: MataKuliahUiState,
    onClick: () -> Unit,
    dosenList: List<Dosen>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMataKuliah(
            mataKuliahEvent = uiState.mataKuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth(),
            dosenList = dosenList
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormMataKuliah(
    mataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    onValueChange: (MataKuliahEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier,
    dosenList: List<Dosen>
) {
    val semester = listOf("Ganjil", "Genap")
    val jenis = listOf("Mata Kuliah Wajib", "Mata Kuliah Pilihan")

    var expanded by remember { mutableStateOf(false) }
    var chosenDropdown by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            value = mataKuliahEvent.nama,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(nama = it))
            },
            label = { Text("Nama", fontSize = 18.sp, color = Color.Black) }, // Mengubah warna label menjadi hitam
            isError = errorState.nama != null,
            placeholder = {
                Text(
                    "Masukkan Nama",
                    fontSize = 16.sp,
                    color = Color.Gray // Warna placeholder yang lebih terang
                )
            },
        )
        Text(text = errorState.nama ?: "", color = Color.Red, fontSize = 14.sp)

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            value = mataKuliahEvent.kode,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(kode = it))
            },
            label = { Text("Kode", fontSize = 18.sp, color = Color.Black) }, // Mengubah warna label menjadi hitam
            isError = errorState.kode != null,
            placeholder = {
                Text(
                    "Masukkan Kode",
                    fontSize = 16.sp,
                    color = Color.Gray // Warna placeholder yang lebih terang
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = errorState.kode ?: "", color = Color.Red, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Semester", color = Color.Black, fontSize = 18.sp) // Mengubah warna teks menjadi hitam
        Row(modifier = Modifier.fillMaxWidth()) {
            semester.forEach { sr ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = mataKuliahEvent.semester == sr,
                        onClick = { onValueChange(mataKuliahEvent.copy(semester = sr)) }
                    )
                    Text(text = sr, color = Color.Black, fontSize = 16.sp) // Mengubah warna teks menjadi hitam
                }
            }
        }
        Text(text = errorState.semester ?: "", color = Color.Red, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            value = mataKuliahEvent.sks,
            onValueChange = {
                onValueChange(mataKuliahEvent.copy(sks = it))
            },
            label = { Text("Sks", fontSize = 18.sp, color = Color.Black) }, // Mengubah warna label menjadi hitam
            isError = errorState.sks != null,
            placeholder = {
                Text(
                    "Masukkan Sks",
                    fontSize = 16.sp,
                    color = Color.Gray // Warna placeholder yang lebih terang
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = errorState.sks ?: "", color = Color.Red, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Jenis", color = Color.Black, fontSize = 18.sp) // Mengubah warna teks menjadi hitam
        Row {
            jenis.forEach { jenis ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = mataKuliahEvent.jenis == jenis,
                        onClick = {
                            onValueChange(mataKuliahEvent.copy(jenis = jenis))
                        }
                    )
                    Text(
                        text = jenis,
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = chosenDropdown,
                onValueChange = { },
                label = { Text("Pilih Dosen Pengampu", fontSize = 18.sp, color = Color.Black) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                placeholder = {
                    Text(
                        "Pilih Dosen Pengampu",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                },
                modifier = Modifier.menuAnchor()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                readOnly = true,
                isError = errorState.dosenPengampu != null
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                dosenList.forEach { dosen ->
                    DropdownMenuItem(
                        onClick = {
                            chosenDropdown = dosen.nama
                            expanded = false
                            onValueChange(mataKuliahEvent.copy(dosenPengampu = dosen.nama))
                        },
                        text = { Text(text = dosen.nama, color = Color.Black) }
                    )
                }
            }
        }
        Text(text = errorState.dosenPengampu ?: "", color = Color.Red, fontSize = 14.sp)
    }
}