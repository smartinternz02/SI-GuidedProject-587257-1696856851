package com.example.travelproject.hfbutton

import androidx.compose.ui.graphics.Color
import com.example.travelproject.R

data class FlightCardData(
    val text:String,
    val imgUri :Int
)

object FlightData{
    val cards = listOf(
        FlightCardData(
            "Air India",
            R.drawable.fairindia
        ),
        FlightCardData(
            "India One",
            R.drawable.findiaone
        ),
        FlightCardData(
            "Indigo",
            R.drawable.findigo
        ),
        FlightCardData(
            "Jet Airways",
            R.drawable.fjet
        ),
        FlightCardData(
            "SpiceJet",
            R.drawable.fspicejet
        ),
        FlightCardData(
            "Vistara",
            R.drawable.fvistara
        ),
    )
}