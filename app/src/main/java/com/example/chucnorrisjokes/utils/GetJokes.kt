package com.example.chucnorrisjokes.utils

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog.TAG
import com.android.volley.toolbox.*
import com.example.chucnorrisjokes.data.JokesData
import com.example.chucnorrisjokes.data.JokesRequest
import com.google.gson.GsonBuilder


class GetJokes(context: Context, private val countJokes: Int){
    private var baseUrl = "http://api.icndb.com/jokes/random/"
    private var jokesArray: ArrayList<JokesData> = ArrayList()
    var jokesArrayList: ArrayList<String> = ArrayList()
    val queue = VolleySingleton(context.applicationContext)

    fun requestToAPI(){
        val gson = GsonBuilder().create()
        if (countJokes > 0){
            baseUrl += countJokes
        }
        val requestToAPI = StringRequest(
            Request.Method.GET, baseUrl,
            Response.Listener<String> { response ->
                val str = gson.fromJson(response.toString(), JokesRequest:: class.java)
                jokesArray.addAll(str.value)
            },
            Response.ErrorListener { error ->
                //Error
                Log.e(TAG, error.message!!)
            }
        )
        queue.requestQueue.add(requestToAPI)
    }

    fun makeJokeArrayList(){
        jokesArrayList.clear()
        for (jokesData in jokesArray){
            jokesArrayList.add(jokesData.joke)
        }
    }

}