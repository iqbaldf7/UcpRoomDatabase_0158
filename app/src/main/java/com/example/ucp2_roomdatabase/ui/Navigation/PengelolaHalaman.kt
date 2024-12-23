package com.example.ucp2_roomdatabase.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2_roomdatabase.ui.view.HomeView
import com.example.ucp2_roomdatabase.ui.view.dosen.DestinasiInsert
import com.example.ucp2_roomdatabase.ui.view.dosen.HomeDosenView
import com.example.ucp2_roomdatabase.ui.view.dosen.InsertDosenView
import com.example.ucp2_roomdatabase.ui.view.matakuliah.DestinasiInsertMataKuliah
import com.example.ucp2_roomdatabase.ui.view.matakuliah.DetailMataKuliahView
import com.example.ucp2_roomdatabase.ui.view.matakuliah.HomeMataKuliahView
import com.example.ucp2_roomdatabase.ui.view.matakuliah.InsertMataKuliahView
import com.example.ucp2_roomdatabase.ui.view.matakuliah.UpdateMataKuliahView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute.route,
        modifier = modifier
    ) {

        composable(route = HomeRoute.route) {
            HomeView(
                onDosenButton = {
                    navController.navigate(DestinasiHomeDosen.route)
                },
                onMataKuliahButton = {
                    navController.navigate(DestinasiHomeMataKuliah.route)
                }
            )
        }


        composable(route = DestinasiHomeDosen.route) {
            HomeDosenView(
                onAddDosen = {
                    navController.navigate(DestinasiInsert.route)
                },
                onBack = { navController.popBackStack() },
                onDetailClick = { nidn ->

                    println("Navigasi ke detail dengan NIDN: $nidn")
                },
            )
        }
        composable(
            route = DestinasiInsert.route
        ) {
            InsertDosenView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }


        composable(route = DestinasiHomeMataKuliah.route) {
            HomeMataKuliahView(
                onAddMk = {
                    navController.navigate(DestinasiInsertMataKuliah.route)
                },
                onDetailClick = { kode ->
                    navController.navigate("${DestinasiDetailMataKuliah.route}/$kode")
                    println("PengelolaHalaman: kode =$kode")
                },
                onBack = { navController.popBackStack() },
                modifier = modifier,
            )
        }
        composable(
            route = DestinasiInsertMataKuliah.route
        ) {
            InsertMataKuliahView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }
        composable(
            DestinasiDetailMataKuliah.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailMataKuliah.KODE_MATAKULIAH) {
                    type = NavType.StringType
                }
            )
        ) {
            val kode = it.arguments?.getString(DestinasiDetailMataKuliah.KODE_MATAKULIAH)
            kode?.let { kode ->
                DetailMataKuliahView(
                    onBack = { navController.popBackStack() },
                    onEditClick = { navController.navigate( "${DestinasiUpdateMataKuliah.route}/$it") },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateMataKuliah.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateMataKuliah.KODE_MATAKULIAH) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMataKuliahView(
                onBack = { navController.popBackStack() },
                onNavigate = { navController.popBackStack() },
                modifier = modifier,
            )
        }
    }
}