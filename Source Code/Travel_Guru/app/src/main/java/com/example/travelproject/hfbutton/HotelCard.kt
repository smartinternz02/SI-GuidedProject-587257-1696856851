package com.example.travelproject.hfbutton

import androidx.compose.ui.graphics.Color
import com.example.travelproject.R

data class HotelCardData(
    val text:String,
    val imgUri :Int
)

object HotelData{
    val cards = listOf(
        HotelCardData(
            "Novotel",
            R.drawable.hnovotel
        ),
        HotelCardData(
            "Radisson",
            R.drawable.hradisson
        ),
        HotelCardData(
            "Westwood Resort",
            R.drawable.hwestwood
        ),
        HotelCardData(
            "Titha Service Villa",
            R.drawable.htitha
        ),
        HotelCardData(
            "Cloud Castle Resorts and Spa",
            R.drawable.hcloud
        ),
        HotelCardData(
            "White House Hotel",
            R.drawable.hwhite
        ),
    )
}