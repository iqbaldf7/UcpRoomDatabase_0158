package com.example.ucp2_roomdatabase.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2_roomdatabase.R

@Composable
fun HomeView(
    onDosenButton: () -> Unit,
    onMataKuliahButton: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF212121),
                        Color(0xFF37474F)
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterResource(R.drawable.umy),
            contentDescription = "Logo Universitas",
            modifier = Modifier.size(450.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Kelola Data Dosen dan Mata Kuliah",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = { onDosenButton() },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .background(Color(0xFF546E7A), shape = RoundedCornerShape(12.dp)),
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Dosen Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Dosen",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }


            Button(
                onClick = { onMataKuliahButton() },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .background(Color(0xFF546E7A), shape = RoundedCornerShape(12.dp)),
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "Mata Kuliah Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White

                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Mata Kuliah",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
