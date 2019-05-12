package com.example.navigationactivity.api

import com.example.navigationactivity.BuildConfig.API_KEY
import com.example.navigationactivity.model.Movie
import retrofit2.http.GET
import retrofit2.Call

interface MovieApi {
    @GET("popular?api_key=" + API_KEY)
    fun getMovie(): Call<List<Movie>>

}