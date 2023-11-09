package com.example.travelproject.login

import android.graphics.Typeface
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.travelproject.R
import com.example.travelproject.ui.theme.darkblue
import java.time.format.TextStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EntryPage(navController: NavHostController) {
    Image(
        painter = painterResource(id = R.drawable.entrybg),
        contentScale =ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize(),
        contentDescription = "Background Image"
    )
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(600.dp))
        Button(
            onClick = { navController.navigate("LoginPage")},
            colors= ButtonDefaults.buttonColors(containerColor = darkblue,contentColor = Color.White)
        )
        {
            Text(text = "Let's Plan!", color = Color.White, fontSize = 29.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}