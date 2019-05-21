package com.example.navigationactivity.listener

import com.example.navigationactivity.model.VideoResponse


interface VideoLoadListener {
    fun onVideoLoaded(videos: VideoResponse)
    fun onVideoLoadError(throwable: Throwable)
}