package com.example.navigationactivity.loaders

import com.example.navigationactivity.listener.CastLoadListener
import com.example.navigationactivity.api.MovieService
import com.example.navigationactivity.model.CastResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CastLoader(val listener: CastLoadListener): Callback<CastResponse> {

    fun loadCast(id: String){
        MovieService.movieApi.getCast(id).enqueue(this)
    }

    override fun onFailure(call: Call<CastResponse>, t: Throwable) {
        listener.onCastLoadError(t)
    }

    override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
        listener.onCastLoaded(response.body()!!)
    }



}
