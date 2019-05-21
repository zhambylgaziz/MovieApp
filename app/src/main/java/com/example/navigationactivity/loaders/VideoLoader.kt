package com.example.navigationactivity.loaders

import com.example.navigationactivity.api.MovieService
import com.example.navigationactivity.listener.VideoLoadListener
import com.example.navigationactivity.model.VideoResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoLoader(val listener: VideoLoadListener): Callback<VideoResponse> {

    fun loadVideos(id: String){
        MovieService.movieApi.getVideos(id).enqueue(this)
    }

    override fun onFailure(call: Call<VideoResponse>, t: Throwable) {
        listener.onVideoLoadError(t)
    }

    override fun onResponse(call: Call<VideoResponse>, response: Response<VideoResponse>) {
        listener.onVideoLoaded(response.body()!!)
    }



}
