package com.example.navigationactivity

import com.example.navigationactivity.model.Movie

interface MovieLoadListener {
    fun onMovieLoaded(movie: Movie)
    fun onMovieLoadError(throwable: Throwable)
}