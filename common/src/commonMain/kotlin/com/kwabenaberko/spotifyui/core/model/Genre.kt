package com.kwabenaberko.spotifyui.core.model

import com.benasher44.uuid.uuid4
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.pop

@OptIn(ExperimentalResourceApi::class)
data class Genre(
    val id: String = uuid4().toString(),
    val name: String,
    val hexColor: String,
    val image: DrawableResource? = null
) {
    companion object {
        val pop = Genre(name = "Pop", hexColor = "#9854B2", image = Res.drawable.pop)
        val indie = Genre(name = "Indie", hexColor = "#678026")
        val newsAndPolitics = Genre(name = "News & Politics", hexColor = "#3371E4")
        val comedy = Genre(name = "Comedy", hexColor = "#CF4321")
        val podcasts = Genre(name = "Podcasts", hexColor = "#223160")
        val charts = Genre(name = "Charts", hexColor = "#8768A7")
        val madeForYou = Genre(name = "Made for you", hexColor = "#75A768")
        val wrapped2021 = Genre(name = "2021 Wrapped", hexColor = "#ABBB6D")
    }
}
