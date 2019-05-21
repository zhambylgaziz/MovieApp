package com.example.navigationactivity.model

import com.google.gson.annotations.SerializedName

data class VideoResponse(
        @SerializedName("id") val id: Long,
        @SerializedName("results") val videos: List<Video>
)

