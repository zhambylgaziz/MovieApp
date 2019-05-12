package com.example.navigationactivity.api

import com.example.navigationactivity.BuildConfig.API_KEY
import com.example.navigationactivity.model.MovieResponse
import retrofit2.http.GET
import retrofit2.Call

interface MovieApi {
    @GET("popular?api_key=" + API_KEY)
    fun getPopularMovies(): Call<MovieResponse>

    @GET("top_rated?api_key=" + API_KEY)
    fun getTopRated(): Call<MovieResponse>

//    @GET("popular?api_key=" + API_KEY)
//    fun getPopularMovies(): Call<List<Movie>>

}