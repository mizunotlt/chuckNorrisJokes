package com.example.chucnorrisjokes.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JokesRequest{
    @SerializedName("type")
    @Expose
    var type: String = ""

    @SerializedName("value")
    @Expose
    var value: Array<JokesData> = arrayOf()

}

data class  JokesData(val id: Int, val joke: String, val categories: Array<String>)