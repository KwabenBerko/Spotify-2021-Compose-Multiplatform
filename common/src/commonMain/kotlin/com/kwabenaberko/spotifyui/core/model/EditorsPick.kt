package com.kwabenaberko.spotifyui.core.model

import com.benasher44.uuid.uuid4

data class EditorsPick(
    val id: String = uuid4().toString(),
    val media: Media
)