package com.example.chucnorrisjokes.data

data class JokesRequest(val types: String, val value : ArrayList<JokesData>)

data class  JokesData(val id: Int, val joke: String, val categories: ArrayList<String>)