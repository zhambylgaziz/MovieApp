package com.example.navigationactivity.listener

import com.example.navigationactivity.model.GenreResponse

interface GenresLoadListener {

    fun onGenresLoaded(genres: GenreResponse)
    fun onGenresLoadError(throwable: Throwable)
}