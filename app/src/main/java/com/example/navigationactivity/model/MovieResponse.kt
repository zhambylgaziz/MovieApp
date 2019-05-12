package com.example.navigationactivity.model

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("page") val page: Long,
    @SerializedName("results") val results: List<Movie>
)