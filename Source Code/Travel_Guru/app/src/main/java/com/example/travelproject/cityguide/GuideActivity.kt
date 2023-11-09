package com.example.travelproject.cityguide

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelproject.ui.theme.TravelprojectTheme

class guideActivity : AppCompatActivity() {

    private val city: Cities by lazy {
        intent?.getSerializableExtra(CITY_ID) as Cities
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelprojectTheme {
                val navController = rememberNavController()
                GuideScreen( city =city)
            }
        }
    }

    companion object {
        private const val CITY_ID = "city_id"
        fun newIntent(context: Context, city: Cities) =
            Intent(context, guideActivity::class.java).apply {
                putExtra(CITY_ID, city)
            }
    }
}