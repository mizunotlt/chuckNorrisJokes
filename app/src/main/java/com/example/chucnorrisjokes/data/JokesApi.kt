package com.example.chucnorrisjokes.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface JokesApi {
    @GET("jokes/random/{count}")
    fun getJokesAsync(@Path("count") count: Int): Deferred<Response<JokesRequest>>
}