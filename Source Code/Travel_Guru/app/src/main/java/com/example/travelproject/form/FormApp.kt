package com.example.travelproject.form

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.travelproject.ui.theme.TravelprojectTheme
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController


@Composable
fun FormApp(navController: NavController){
    TravelprojectTheme {
        Surface (
            modifier= Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
            ){
            AddFormScreen(navController = rememberNavController())
        }
    }
}