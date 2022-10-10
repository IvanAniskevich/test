package com.example.testapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
//    https://www.nbrb.by/api/exrates/rates?ondate=$Date&periodicity=0
    @GET("/api/exrates/rates")
    suspend fun getItems(
        @Query("ondate") data: String,
        @Query("periodicity") periodicity: String
    ): ArrayList<ItemJson>

}