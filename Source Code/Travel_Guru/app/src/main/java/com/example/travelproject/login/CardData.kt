package com.example.travelproject.login

import com.example.travelproject.R

data class cardData(
    val title : String,
    val reting: Float,
    val desc : String,
    val imgUri :Int
)

/* Create Card Data List */

val cardList = listOf(
    cardData(
        title = "Hampi",
        reting=2.0f,
        desc = "Step into history's embrace and explore the ancient wonders of Hampi with our app",
        R.drawable.hampi
    ),

    cardData(
        title = "Kulu Manali",
        reting=4.0f,
        desc = "Experience the enchanting beauty of Kullu Manali through our app",
        R.drawable.kulumanali
    ),
    cardData(
        title = "Ladakh",
        reting=4.0f,
        desc = "Discover the rugged beauty of Ladakh, where stark landscapes and adventure unite, through our app",
        R.drawable.ladakh
    ),
    cardData(
        title = "Goa",
        reting=4.0f,
        desc = "Discover Goa's sun-kissed beaches and electrifying nightlife",
        R.drawable.goa
    )
)