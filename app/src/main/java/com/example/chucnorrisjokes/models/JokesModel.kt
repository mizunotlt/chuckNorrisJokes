package com.example.chucnorrisjokes.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chucnorrisjokes.data.JokesApiFactory
import com.example.chucnorrisjokes.data.JokesData
import com.example.chucnorrisjokes.repository.JokesRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class JokesModel: ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : JokesRepository = JokesRepository(JokesApiFactory.jokesApi)

    val jokesLiveData = MutableLiveData<ArrayList<JokesData>>().apply { value = arrayListOf() }

    fun getJokes(jokesCount: Int){
        scope.launch {
            val jokes = repository.getJokesList(jokesCount)
            jokesLiveData.postValue(jokes)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}