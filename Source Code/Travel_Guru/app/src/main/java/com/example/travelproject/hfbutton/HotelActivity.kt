package com.example.travelproject.hfbutton

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.travelproject.R
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context

/*
class HotelActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val painter = painterResource(id = R.drawable.ic_launcher_background)
            val description = "5 star"
            val title = "Novotel"

            Box(
                modifier=Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                HotelImageCard(
                    painter = painter,
                    contentDescription = description,
                    title = title
                )
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HotelImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier =Modifier
){
    Card(
        modifier=modifier.fillMaxWidth(),
        shape= RoundedCornerShape(15.dp),
        elevation=5.dp
    ){
        Box(modifier=Modifier.height(200.dp)){
            Image(
                painter=painter,
                contentDescription=contentDescription,
                contentScale= ContentScale.Crop
            )
            Box(modifier= Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors=listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY=300f
                    )
                ))
            Box(
                modifier= Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(title, style = androidx.compose.ui.text.TextStyle(color= Color.White), fontSize = 16.sp)
            }
        }
    }
}*/

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HotelImageCard(navController: NavHostController){
    val cards =HotelData.cards
    Column {
        val context = LocalContext.current
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Transparent)
                ) {
                    Image(painterResource(id = R.drawable.logo) , contentDescription = "My Logo",
                        // this code is to set logo
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.Center)
                            .clickable {
                                Toast
                                    .makeText(context, "Refresh", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    )}
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "   Check out our Hotel Options",
            color = Color.Black,
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyVerticalGrid(
            columns =GridCells.Fixed(2)  ,
            content = {
                items(items=cards, itemContent = {
                    card(card = it)
                })
            }
        )
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun card(card:HotelCardData) {

    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .width(400.dp)
            .height(200.dp),
                elevation =5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            //horizontalArrangement = Arrangement.Center,
            //verticalAlignment = Alignment.CenterVertically
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                card.text,
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Image(
                painter = painterResource(card.imgUri),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

        }
    }
}
