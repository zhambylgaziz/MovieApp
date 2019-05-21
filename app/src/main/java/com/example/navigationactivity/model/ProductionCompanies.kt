package com.example.navigationactivity.model

import com.google.gson.annotations.SerializedName

data class ProductionCompanies (
    @SerializedName("id") val id: Long,
    @SerializedName("logo_path") val logo: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val country: String
)