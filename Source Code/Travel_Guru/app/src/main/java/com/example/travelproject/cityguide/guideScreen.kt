package com.example.travelproject.cityguide

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
//import androidx.compose.material3.ContentAlpha
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelproject.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideScreen(
    city: Cities, onNavIconPressed: () -> Unit = { }
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {

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
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    ProfileHeader(
                        scrollState,
                        city,
                        this@BoxWithConstraints.maxHeight
                    )
                    ProfileContent(city, this@BoxWithConstraints.maxHeight)
                }
            }
        }
       /* Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.CenterHorizontally),
            onClick ={ navController.navigate("HotelImageCard") },
            shape=MaterialTheme.shapes.extraLarge
        ){
            Text(text= stringResource(id =R.string.hotels),
                style =MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.CenterHorizontally),
            onClick ={ navController.navigate("FlightImageCard") },
            shape=MaterialTheme.shapes.extraLarge
        ){
            Text(text= stringResource(id = R.string.flights),
                style =MaterialTheme.typography.bodyLarge)
        }*/
    }
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    city: Cities,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = painterResource(id = city.cityImage),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun ProfileContent(city: Cities, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Name(city)

        ProfileProperty(stringResource(R.string.title), city.title)

        ProfileProperty(stringResource(R.string.description), city.description)

        Image(
            modifier = Modifier
                .heightIn(max = containerHeight / 2)
                .fillMaxWidth(),
            painter = painterResource(id = city.mapImage),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        //Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Name(
    city: Cities
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            city = city,
            modifier = Modifier.baselineHeight(32.dp)
        )
    }
}

@Composable
private fun Name(city: Cities, modifier: Modifier = Modifier) {
    Text(
        text = city.title,
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
            Text(
                text = label,
                modifier = Modifier.baselineHeight(24.dp),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
        } else {
            MaterialTheme.typography.bodyLarge
        }
        Text(
            text = value,
            modifier = Modifier.baselineHeight(24.dp),
            style = style
        )
    }
}


@Preview
@Composable
fun ProfilePreview() {
    val city = data.city
    val navController = rememberNavController()
    GuideScreen(city =city)
}