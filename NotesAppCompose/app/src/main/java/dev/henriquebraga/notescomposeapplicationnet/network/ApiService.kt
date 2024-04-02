package dev.henriquebraga.notescomposeapplicationnet.network

import dev.henriquebraga.notescomposeapplicationnet.model.Note
import dev.henriquebraga.notescomposeapplicationnet.model.NoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {


    @GET("/get/all")
    suspend fun getNotes(): Response<List<Note>>

    @GET("/get/{id}")
    suspend fun getNote(@Path("id") noteId: Int): Response<Note>

    @POST("/insert")
    suspend fun insert(@Body note: Note): Response<NoteResponse>


}