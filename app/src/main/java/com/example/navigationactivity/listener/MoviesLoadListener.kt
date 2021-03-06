package com.example.navigationactivity.listener

import com.example.navigationactivity.model.MovieResponse

interface MoviesLoadListener {
    fun onMoviesLoaded(movies: MovieResponse, type: Int)
    fun onMoviesLoadError(throwable: Throwable)
}