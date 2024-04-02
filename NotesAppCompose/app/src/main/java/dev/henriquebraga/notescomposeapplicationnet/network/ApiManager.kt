package dev.henriquebraga.notescomposeapplicationnet.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    private const val BASE_URL = "http://192.168.1.160:8081"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}