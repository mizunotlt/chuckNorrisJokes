package com.example.chucnorrisjokes.repository

import android.util.Log
import com.example.chucnorrisjokes.data.JokesApi
import com.example.chucnorrisjokes.data.JokesData
import retrofit2.Response
import java.io.IOException

class JokesRepository (private val api: JokesApi): BaseRepository(){

    suspend fun getJokesList(id: Int): ArrayList<JokesData>?{
        val jokesResponse = safeApiCall(
            call = { api.getJokesAsync(id).await()},
            errorMessage = "Error Getting Jokes"
        )
        return jokesResponse?.value
    }

}

open class BaseRepository{

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T>{
        val response = call.invoke()
        if(response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error - $errorMessage"))
    }
}

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}