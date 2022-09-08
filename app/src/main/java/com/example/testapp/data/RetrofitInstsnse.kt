package com.example.testapp.data


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitInstsnse {

  private val retrofit =
      Retrofit.Builder()
          .baseUrl("https://www.nbrb.by/")
          .addConverterFactory(GsonConverterFactory.create())
          .build()


    val API_SERVICES: ApiServices by lazy {
        retrofit.create(ApiServices::class.java)
    }
}