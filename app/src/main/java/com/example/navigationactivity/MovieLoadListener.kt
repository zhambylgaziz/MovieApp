package com.example.navigationactivity

import com.example.navigationactivity.model.Movie

interface MovieLoadListener {
    fun onMoviesLoaded(movies: List<Movie>, type: Int)
    fun onMoviesLoadError(throwable: Throwable)
}