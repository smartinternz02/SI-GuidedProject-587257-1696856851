package com.example.travelproject.cityguide

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.travelproject.R
import com.example.travelproject.cityguide.data.city
import com.example.travelproject.login.ViewPagerSlider
import com.example.travelproject.login.cardList
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.gms.wallet.button.ButtonConstants


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Homescreen(navigateToProfile: (Cities) -> Unit,navHostController: NavHostController) {
    val cities = remember { data.cityList }
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        item {
            /*TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = "App Bar Logo",
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                modifier = Modifier.padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .background(color = Color.Magenta),
            )*/
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
        item { ViewPagerSlider(navHostController = navHostController) }

       /* items(
            items = cardList,
            itemContent = { card ->
                ViewPagerSlider(navHostController = navHostController)
            }
        )*/
        item {
            Row {
                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFC0E7FF),
                        contentColor = Color.Black),
                            onClick = { navHostController.navigate("HotelImageCard") },
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = stringResource(id = R.string.hotels),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .height(56.dp),
                    colors =ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFC0E7FF),
                        contentColor = Color.Black),
                    onClick = { navHostController.navigate("FlightImageCard") },
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = stringResource(id = R.string.flights),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "    Choose your Destination",
                color = Color.Black,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(
            items = cities,
            itemContent = {
                CityListItem(city = it, navigateToProfile)
            }
        )
    }

}

