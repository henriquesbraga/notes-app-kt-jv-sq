package com.henriquesbraga.notesappjv.api;

import com.henriquesbraga.notesappjv.model.Note;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("insert/")
    Call<Note> saveNote(@Body RequestBody params);

    @GET("get/all/")
    Call<List<Note>> getNotes();

    @Headers("Content-Type: application/json")
    @PUT("update/")
    Call<Note> updateNote(@Body RequestBody params);

    @Headers("Content-Type: application/json")
    @DELETE("delete/{id}")
    Call<Note> deleteNote(@Path("id") int id);
}
