package com.example.testapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/api/exrates/rates")
    suspend fun getItemsToday(
        @Query("ondate") data: String,
        @Query("periodicity") periodicity: String
    ): ArrayList<ItemJson>

    @GET("/api/exrates/rates")
    suspend fun getItemsTomorrow(
        @Query("ondate") data: String,
        @Query("periodicity") periodicity: String
    ): ArrayList<ItemJson>
}