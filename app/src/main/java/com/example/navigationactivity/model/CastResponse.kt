package com.example.navigationactivity.model

import com.google.gson.annotations.SerializedName

data class CastResponse (
    @SerializedName("id") val id: Long,
    @SerializedName("cast") val cast: List<Cast>
)