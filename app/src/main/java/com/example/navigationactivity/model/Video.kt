package com.example.navigationactivity.model

import com.google.gson.annotations.SerializedName

data class  Video (
    @SerializedName("id") val id: String,
    @SerializedName("key") val key: String,
    @SerializedName("site") val site: String,
    @SerializedName("size") val size: Int,
    @SerializedName("name") val name: String
)


