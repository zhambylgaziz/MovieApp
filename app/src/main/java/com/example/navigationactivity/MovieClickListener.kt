package com.example.navigationactivity

import com.example.navigationactivity.model.Movie

interface MovieClickListener {

    fun onMovieClicked(movie: Movie)
}