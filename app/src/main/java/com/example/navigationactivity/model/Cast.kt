package com.example.navigationactivity.model

import com.google.gson.annotations.SerializedName

data class Cast (
    @SerializedName("cast_id") val id: Long,
    @SerializedName("character") val character: String,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profile_path: String
)