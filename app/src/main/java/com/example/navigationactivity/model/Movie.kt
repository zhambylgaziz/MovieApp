package com.example.navigationactivity.model

import com.google.gson.annotations.SerializedName

data class  Movie (
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanies>
)


