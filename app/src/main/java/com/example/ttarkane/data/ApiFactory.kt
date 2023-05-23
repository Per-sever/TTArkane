package com.example.ttarkane.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService = retrofit.create(ApiService::class.java)

}