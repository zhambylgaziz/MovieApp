package com.example.navigationactivity.model

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("results") val movies: List<Movie>
)