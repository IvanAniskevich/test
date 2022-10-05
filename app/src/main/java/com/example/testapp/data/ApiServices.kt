package com.example.testapp.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
//    @GET("/api/exrates/rates?ondate=2022-10-3&periodicity=0")
//    suspend fun getItemsToday(): ArrayList<ItemJson>

    @GET("/api/exrates/rates?ondate={data}&periodicity=0")
    suspend fun getItemsToday(@Path("data", encoded = true)data: String): ArrayList<ItemJson>


//    @GET("/api/exrates/rates?ondate=2022-10-4&periodicity=0")
//    suspend fun getItemsTomorrow(): ArrayList<ItemJson>
//
    @GET("/api/exrates/rates?ondate={data}&periodicity=0")
    suspend fun getItemsTomorrow(@Path("data", encoded = true) data: String): ArrayList<ItemJson>

}