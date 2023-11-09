package com.example.travelproject.login

import android.graphics.PorterDuff
import android.widget.RatingBar
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.travelproject.R
import com.example.travelproject.login.cardList
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import java.lang.Thread.yield
import kotlin.math.absoluteValue
import androidx.compose.material3.TopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalPagerApi
@Composable
fun ViewPagerSlider(navHostController: NavHostController) {
    val pagerState = rememberPagerState(
        //pageCount = cardList.size,
        initialPage = 2
    )
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )

        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        /*Column(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(color = Color.Green),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            Text(
//                text = "View Pager Slide",
//                color = Color.White,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            )
            val context = LocalContext.current
           TopAppBar(



                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Blue)
                    ) {
                        Image(painterResource(id = R.drawable.kulumanali) , contentDescription = "My Logo",
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

//       title = {Text(text = "Hello Hi How are You what are you doing?", // this is for content
//           fontSize = 20.sp,
//       maxLines = 1,
//       overflow = TextOverflow.Ellipsis)},

                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Menu,"Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        /*TODO*/
                    }) {
                        Icon(Icons.Default.Search,"Search")
                    }
                },
                )
        }*/

        Spacer(modifier = Modifier.height(30.dp))
        HorizontalPager(
            state = pagerState,

            count = cardList.size,

            modifier = Modifier
                .padding(0.dp, 40.dp, 0.dp, 40.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffSet = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale

                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                        )

                    }
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {

                val newCards = cardList[page]
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .width(400.dp)
                        .background(color = Color.Gray)

                ) {
                    Image(
                        painter = painterResource(
                            id = newCards.imgUri
                        ),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(300.dp)
                            .width(400.dp)
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(15.dp)
                    ) {
                        Text(
                            text = newCards.title,
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        val ratingBar = RatingBar(
                            LocalContext.current, null, android.R.attr.ratingBarStyleSmall
                        ).apply {
                            rating = newCards.reting
                            progressDrawable.setColorFilter(
                                android.graphics.Color.parseColor("#FF0000"),
                                PorterDuff.Mode.SRC_ATOP
                            )

                        }

                        AndroidView(
                            factory = { ratingBar },
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )

                        Text(
                            text = newCards.desc,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )
                    }
                }
            }
        }


        //Horizontal dot indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}