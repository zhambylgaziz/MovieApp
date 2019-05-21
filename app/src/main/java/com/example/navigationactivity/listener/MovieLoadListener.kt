package com.example.navigationactivity.listener

import com.example.navigationactivity.model.Movie

interface MovieLoadListener {
    fun onMovieLoaded(movie: Movie)
    fun onMovieLoadError(throwable: Throwable)
}