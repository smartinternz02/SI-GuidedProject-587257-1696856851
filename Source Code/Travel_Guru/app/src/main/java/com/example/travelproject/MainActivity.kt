package com.example.travelproject

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.travelproject.cityguide.Cities
import com.example.travelproject.cityguide.Homescreen
import com.example.travelproject.form.AddFormScreen
import com.example.travelproject.ui.theme.TravelprojectTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.travelproject.cityguide.GuideScreen
import com.example.travelproject.cityguide.data.city
import com.example.travelproject.cityguide.guideActivity
import com.example.travelproject.hfbutton.FlightImageCard
import com.example.travelproject.hfbutton.HotelImageCard
import com.example.travelproject.login.EntryPage
import com.example.travelproject.login.ForgetPswrdPage
//import com.example.travelproject.login.HomePage
import com.example.travelproject.login.LoginPage
import com.example.travelproject.login.SignUpPage
import com.example.travelproject.login.ViewPagerSlider
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelprojectTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    MyApp{startActivity(guideActivity.newIntent(this, it))}
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun MyApp(navigateToProfile: (Cities) -> Unit) {
    val navController = rememberNavController()

   /* NavHost(navController = navController, startDestination = "AddFormScreen") {
        composable("AddFormScreen") {
            AddFormScreen(navController = navController)
        }
        composable("Homescreen") {
            Homescreen(navigateToProfile = navigateToProfile)
        }
    }*/
    Scaffold(
        content = {
            NavHost(navController = navController, startDestination = "EntryPage") {
                composable("AddFormScreen") {
                    AddFormScreen(navController = navController)
                }
                composable("Homescreen") {
                    Homescreen(navigateToProfile = navigateToProfile, navHostController = navController)
                }
                composable("guideScreen"){
                    GuideScreen(city = city)
                }

                composable("EntryPage"){EntryPage(navController) }
                composable("LoginPage"){LoginPage(navController)}
                composable("SignUpPage"){SignUpPage(navController)}
                composable("ForgetPswrdPage"){ForgetPswrdPage(navController)}
                //composable("HomePage"){ HomePage(navController) }
                composable("HotelImageCard"){ HotelImageCard(navController)}
                composable("FlightImageCard"){ FlightImageCard(navController)}
                composable("ViewPagerSlider"){ViewPagerSlider(navController)}
            }
            //Homescreen(navigateToProfile = navigateToProfile)
        }
    )
}


/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormApp(navController = rememberNavController())
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(navigateToProfile: (Cities) -> Unit) {
   Scaffold(
      content = {
           Homescreen(navigateToProfile = navigateToProfile)
        })
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    TravelprojectTheme {
        MyApp { }
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    TravelprojectTheme(darkTheme = true) {
        MyApp { }
    }
}*/