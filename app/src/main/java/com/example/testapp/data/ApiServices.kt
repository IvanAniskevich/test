package com.example.testapp.data

import retrofit2.http.GET

interface ApiServices {
    @GET("api/exrates/rates?ondate=2016-7-6&periodicity=0")
   suspend fun getItemsToday(): ArrayList<ItemJson>

    @GET("api/exrates/rates?ondate=2022-9-9&periodicity=0")
    suspend fun getItemsTomorrow(): ArrayList<ItemJson>
}