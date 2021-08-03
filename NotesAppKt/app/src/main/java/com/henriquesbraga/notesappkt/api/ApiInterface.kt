package com.henriquesbraga.notesappkt.api

import com.henriquesbraga.notesappkt.model.Note
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("insert/")
    fun saveNote(@Body params: RequestBody): Call<Note>

    @GET("get/all/")
    fun getNotes(): Call<ArrayList<Note>>

    @Headers("Content-Type: application/json")
    @PUT("update/")
    fun updateNote(@Body params: RequestBody): Call<Note>

    @Headers("Content-Type: application/json")
    @DELETE("delete/{id}")
    fun deleteNote(@Path("id") id: Int): Call<Note>
}