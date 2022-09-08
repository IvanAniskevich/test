package com.example.testapp.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("api/exrates/rates?ondate=2016-7-6&periodicity=0")
   suspend fun getItemsToday(): ArrayList<ItemsJson>

    @GET("api/exrates/rates?ondate=2016-7-7&periodicity=0")
    fun getItemsTomorrow(): ArrayList<ItemsJson>
}