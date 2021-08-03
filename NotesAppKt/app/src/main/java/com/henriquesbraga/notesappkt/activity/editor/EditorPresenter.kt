package com.henriquesbraga.notesappkt.activity.editor

import androidx.collection.ArrayMap
import com.henriquesbraga.notesappkt.api.ApiClient
import com.henriquesbraga.notesappkt.api.ApiInterface
import com.henriquesbraga.notesappkt.model.Note
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditorPresenter(view: EditorView) {

    private var view = view

    fun saveNote(note: Note){
        view.showProgress()

        var apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

        //request body
        val jsonParams: MutableMap<String, Any> = ArrayMap()
        jsonParams["title"] = note.title
        jsonParams["note"] = note.note
        jsonParams["color"] = note.color

        val requestBody: RequestBody = RequestBody.create(
            okhttp3.MediaType.parse("application/json; charset=utf-8"),
            (JSONObject(jsonParams as Map<*, *>)).toString());

        var call: Call<Note> = apiInterface.saveNote(requestBody)
        call.enqueue(object: Callback<Note>{
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                view.hideProgress()
                if(response.isSuccessful && response.body() != null){
                    var success: Boolean = response.body()!!.success
                    if(success) { view.onRequestSuccess(response.body()!!.message)}
                    else{ view.onRequestError(response.body()!!.message) }
                }
            }

            override fun onFailure(call: Call<Note>, t: Throwable) {
                view.hideProgress()
                view.onRequestError(t.localizedMessage)
            }
        })
    }

    fun updateNote(note: Note){

        view.showProgress()
        var apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

        //request body
        val jsonParams: MutableMap<String, Any> = ArrayMap()
        jsonParams["id"] = note.id!!
        jsonParams["title"] = note.title
        jsonParams["note"] = note.note
        jsonParams["color"] = note.color

        val requestBody: RequestBody = RequestBody.create(
            okhttp3.MediaType.parse("application/json; charset=utf-8"),
            (JSONObject(jsonParams as Map<*, *>)).toString());

        var call: Call<Note> = apiInterface.updateNote(requestBody)
        call.enqueue(object: Callback<Note>{
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                view.hideProgress()
                if(response.isSuccessful && response.body() != null){
                    var success: Boolean = response.body()!!.success
                    if(success) { view.onRequestSuccess(response.body()!!.message)}
                    else{ view.onRequestError(response.body()!!.message) }
                }
            }
            override fun onFailure(call: Call<Note>, t: Throwable) {
                view.hideProgress()
                view.onRequestError(t.localizedMessage)
            }
        })

    }

    fun deleteNote(id: Int){
        view.showProgress()
        var apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        var call: Call<Note> = apiInterface.deleteNote(id)
        call.enqueue(object: Callback<Note>{
            override fun onResponse(call: Call<Note>, response: Response<Note>) {
                view.hideProgress()
                if(response.isSuccessful && response.body() != null){
                    var success: Boolean = response.body()!!.success
                    if(success) { view.onRequestSuccess(response.body()!!.message)}
                    else{ view.onRequestError(response.body()!!.message) }
                }
            }
            override fun onFailure(call: Call<Note>, t: Throwable) {
                view.hideProgress()
                view.onRequestError(t.localizedMessage)
            }
        })
    }
}