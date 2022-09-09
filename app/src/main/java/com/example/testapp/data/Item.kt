package com.example.testapp.data

data class Item(
    val Cur_Abbreviation: String,
    val Cur_ID: Int,
    val Cur_Name: String,
    val Cur_OfficialRateToday: Double,
    val Cur_OfficialRateTomorrow: Double,
    val Cur_Scale: Int,
    val Date: String,
    var Visibility: Boolean
)