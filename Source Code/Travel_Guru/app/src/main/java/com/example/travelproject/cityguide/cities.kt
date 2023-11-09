package com.example.travelproject.cityguide

import java.io.Serializable

data class Cities(
    val title: String,
    val description: String,
    val cityImage: Int = 0,
    val mapImage: Int =0
) : Serializable