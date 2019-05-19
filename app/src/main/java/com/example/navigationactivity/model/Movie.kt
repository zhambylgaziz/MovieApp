package com.example.navigationactivity.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class  Movie (
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("genres") val genres: List<Genre>
)


