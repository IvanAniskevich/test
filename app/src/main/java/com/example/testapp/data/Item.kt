package com.example.testapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "item_database")
data class Item(
    val Cur_Abbreviation: String,
    val Cur_ID: Int,
    @PrimaryKey
    val Cur_Name: String,
    val Cur_OfficialRateToday: Double,
    val Cur_OfficialRateTomorrow: Double,
    val Cur_Scale: Int,
    val Date: String,
    var Visibility: Boolean
)