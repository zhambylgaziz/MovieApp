package com.example.navigationactivity.loaders

import com.example.navigationactivity.MovieLoadListener
import com.example.navigationactivity.api.MovieService
import com.example.navigationactivity.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
const val TYPE_TOP_RATED = 1
class TopRatedMoviesLoader(val listener: MovieLoadListener): Callback<List<Movie>> {

    fun loadMovies(){
        MovieService.movieApi.getTopRated().enqueue(this)
    }

    override fun onFailure(call: Call<List<Movie>>, t: Throwable){
        listener.onMoviesLoadError(t)
    }

    override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>){
        listener.onMoviesLoaded(response.body()!!, TYPE_TOP_RATED)
    }

}