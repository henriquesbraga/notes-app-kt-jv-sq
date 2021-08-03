package com.henriquesbraga.notesappkt.activity.main

import com.henriquesbraga.notesappkt.api.ApiClient
import com.henriquesbraga.notesappkt.api.ApiInterface
import com.henriquesbraga.notesappkt.model.Note
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(view: MainView) {

    private var view = view

    fun getData(){
        view.showLoading();
        val apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        var call: Call<ArrayList<Note>> = apiInterface.getNotes()
        call.enqueue(object: Callback<ArrayList<Note>> {
            override fun onResponse(call: Call<ArrayList<Note>>, response: Response<ArrayList<Note>>) {
                view.hideLoading()
                if(response.isSuccessful && response.body() != null){
                    view.onGetResult((response.body()!!))
                }
            }

            override fun onFailure(call: Call<ArrayList<Note>>, t: Throwable) {
                view.hideLoading()
                view.onErrorLoading(t.localizedMessage)
            }
        })
    }
}