package com.example.navigationactivity.loaders

import com.example.navigationactivity.MoviesLoadListener
import com.example.navigationactivity.api.MovieService
import com.example.navigationactivity.model.Movie
import com.example.navigationactivity.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TYPE_RECOMMENDED = 2
class RecommendedMoviesLoader(val listener: MoviesLoadListener): Callback<MovieResponse> {

    fun loadMovies(movie: Movie){
        MovieService.movieApi.getRecomendations(movie.id.toString()).enqueue(this)
    }

    override fun onFailure(call: Call<MovieResponse>, t: Throwable){
        listener.onMoviesLoadError(t)
    }

    override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>){
        listener.onMoviesLoaded(response.body()!!, TYPE_RECOMMENDED)
    }

}