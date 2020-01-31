package com.example.chucnorrisjokes.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JokesApiFactory {
    private val jokesClient = OkHttpClient().newBuilder()
        .addInterceptor(interceptor())
        .build()

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level= HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(jokesClient)
        .baseUrl("http://api.icndb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val jokesApi : JokesApi = retrofit().create(JokesApi::class.java)
}