package com.kwabenaberko.spotifyui.core.model

import com.benasher44.uuid.uuid4
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.ed_sheeran_big_sean_juice_wrld_post_malone
import spotifyui.common.generated.resources.front_left
import spotifyui.common.generated.resources.indie_pop
import spotifyui.common.generated.resources.mitski_tame_impala_glass_animals_charlie_xcx
import spotifyui.common.generated.resources.todays_top_hits

@OptIn(ExperimentalResourceApi::class)
data class Playlist(
    val id: String = uuid4().toString(),
    val title: String? = null,
    val image: DrawableResource? = null,
    val tracks: List<Track> = emptyList(),
    val isEditorsPick: Boolean = false
) : Media {
    companion object {
        val indiePop = Playlist(title = "Indie Pop", image = Res.drawable.indie_pop)
        val edSheeranBigSeanJuiceWRLDPostMalone = Playlist(
            title = "Ed Sheeran, Big Sean, Juice WRLD, Post Malone",
            image = Res.drawable.ed_sheeran_big_sean_juice_wrld_post_malone
        )
        val mitskiTameImpalaGlassAnimalsCharlieXCX = Playlist(
            title = "Mitski, Tame Impala, Glass Animals, Charlie XCX",
            image = Res.drawable.mitski_tame_impala_glass_animals_charlie_xcx
        )
        val todaysTopHits = Playlist(image = Res.drawable.todays_top_hits)
        val frontLeft = Playlist(title = "Front Left", image = Res.drawable.front_left)
    }
}
