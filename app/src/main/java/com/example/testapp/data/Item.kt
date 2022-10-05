package com.example.testapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_database")
data class Item(
    @PrimaryKey
    var Cur_Abbreviation: String,
    var Cur_ID: Int,
    var Cur_Name: String,
    var Cur_OfficialRateToday: Double,
    var Cur_OfficialRateTomorrow: Double,
    var Cur_Scale: Int,
    var Date: String,
    var Visibility: Boolean
)