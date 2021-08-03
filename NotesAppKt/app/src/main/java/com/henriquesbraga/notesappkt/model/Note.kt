package com.henriquesbraga.notesappkt.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Note(
    id: Int?,
    title: String,
    note: String,
    color: Int
) {

    @Expose
    @SerializedName("id")
    var id = id

    @Expose
    @SerializedName("title")
    var title = title

    @Expose
    @SerializedName("note")
    var note = note

    @Expose
    @SerializedName("color")
    var color = color

    @Expose
    @SerializedName("date")
    var date = ""

    @Expose
    @SerializedName("success")
    var success = false

    @Expose
    @SerializedName("message")
    var message = ""


}