package com.henriquesbraga.notesappjv.activity.editor;

import android.util.ArrayMap;

import androidx.annotation.NonNull;

import com.henriquesbraga.notesappjv.api.ApiClient;
import com.henriquesbraga.notesappjv.api.ApiInterface;
import com.henriquesbraga.notesappjv.model.Note;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view){
        this.view = view;
    }

    void saveNote(final String title, final String note, final int color) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("title", title);
        jsonParams.put("note", note);
        jsonParams.put("color", color);

        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());

        Call<Note> call = apiInterface.saveNote(requestBody);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else{
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void updateNote(int id, String title, String note, int color){
        view.showProgress();

        Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("id", id);
        jsonParams.put("title", title);
        jsonParams.put("note", note);
        jsonParams.put("color", color);
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.updateNote(requestBody);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError((t.getLocalizedMessage()));
            }
        });
    }

    void deleteNote(int id) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.deleteNote(id);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError((t.getLocalizedMessage()));
            }
        });

    }
}

