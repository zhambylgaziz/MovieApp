package com.example.navigationactivity.model

import com.google.gson.annotations.SerializedName


data class GenreResponse (
    @SerializedName("genres") val genres: List<Genre>
)