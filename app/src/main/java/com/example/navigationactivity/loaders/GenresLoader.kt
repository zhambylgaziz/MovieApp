package com.example.navigationactivity.loaders

import com.example.navigationactivity.GenresLoadListener
import com.example.navigationactivity.api.MovieService
import com.example.navigationactivity.model.GenreResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresLoader(val listener: GenresLoadListener): Callback<GenreResponse> {

    fun loadGenres(){
        MovieService.movieApi.getGenres().enqueue(this)
    }

    override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
        listener.onGenresLoadError(t)
    }

    override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
        listener.onGenresLoaded(response.body()!!)
    }



}
