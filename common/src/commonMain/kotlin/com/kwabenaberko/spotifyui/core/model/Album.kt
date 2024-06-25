package com.kwabenaberko.spotifyui.core.model

import com.benasher44.uuid.uuid4
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.one_remastered

@OptIn(ExperimentalResourceApi::class)
data class Album(
    val id: String = uuid4().toString(),
    val title: String,
    val artist: Artist,
    val image: DrawableResource? = null,
    val isEditorsPick: Boolean = false
) : Media {
    companion object {
        val oneRemastered = Album(
            title = "1(Remastered)", artist = Artist.theBeatles, image = Res.drawable.one_remastered
        )
    }
}
