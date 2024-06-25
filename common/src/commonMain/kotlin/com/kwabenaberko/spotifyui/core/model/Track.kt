package com.kwabenaberko.spotifyui.core.model

import com.benasher44.uuid.uuid4
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.les

@OptIn(ExperimentalResourceApi::class)
data class Track(
    val id: String = uuid4().toString(),
    val title: String,
    val image: DrawableResource? = null,
    val artist: Artist,
    val isEditorsPick: Boolean = false
) : Media {
    companion object {
        val les = Track(
            title = "Les",
            image = Res.drawable.les,
            artist = Artist.childishGambino,
            isEditorsPick = true
        )
    }
}