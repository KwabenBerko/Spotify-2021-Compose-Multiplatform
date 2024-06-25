package com.kwabenaberko.spotifyui.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class IsUserLoggedIn {
    val flow = MutableStateFlow(false)

    fun execute(): Flow<Boolean> {
        return flow
    }

    companion object {
        val INSTANCE by lazy { IsUserLoggedIn() }
    }
}
