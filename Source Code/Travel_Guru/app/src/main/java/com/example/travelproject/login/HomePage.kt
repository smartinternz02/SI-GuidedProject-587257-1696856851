package com.example.travelproject.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.travelproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .background(color = Color.Transparent)
            ) {
                Image(painterResource(id = R.drawable.logo) , contentDescription = "My Logo",
                    // this code is to set logo
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center)
                        .clickable {
                            Toast.makeText(context,"Refresh",Toast.LENGTH_SHORT).show()
                        }
                )}
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Menu,"Menu")
            }
        },
        actions = {
            IconButton(onClick = {
            }) {
                Icon(Icons.Default.Search,"Search")
            }
        },
    )
}

