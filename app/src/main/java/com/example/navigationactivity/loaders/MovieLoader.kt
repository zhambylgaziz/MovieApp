package com.example.navigationactivity.loaders

import com.example.navigationactivity.MovieLoadListener
import com.example.navigationactivity.api.MovieService
import com.example.navigationactivity.model.Movie

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieLoader(val listener: MovieLoadListener): Callback<Movie> {

    fun loadMovie(id: String?){
        MovieService.movieApi.getMovie(id).enqueue(this)
    }

    override fun onFailure(call: Call<Movie>, t: Throwable){
        listener.onMovieLoadError(t)
    }

    override fun onResponse(call: Call<Movie>, response: Response<Movie>){
        listener.onMovieLoaded(response.body()!!)
    }

}