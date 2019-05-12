package com.example.navigationactivity

import com.example.navigationactivity.model.Movie
import com.example.navigationactivity.model.MovieResponse

interface MovieLoadListener {
    fun onMoviesLoaded(movies: MovieResponse, type: Int)
    fun onMoviesLoadError(throwable: Throwable)
}