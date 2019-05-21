package com.example.navigationactivity.listener

import com.example.navigationactivity.model.CastResponse

interface CastLoadListener {
    fun onCastLoaded(casts: CastResponse)
    fun onCastLoadError(throwable: Throwable)
}