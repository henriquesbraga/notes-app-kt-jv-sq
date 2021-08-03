package com.henriquesbraga.notesappkt.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        private val BASE_URL = "http://192.168.1.100:8081/"
        private lateinit var retrofit: Retrofit

        fun getApiClient(): Retrofit {
            if(!::retrofit.isInitialized){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}