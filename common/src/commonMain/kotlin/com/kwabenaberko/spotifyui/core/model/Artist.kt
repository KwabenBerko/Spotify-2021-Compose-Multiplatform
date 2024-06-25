package com.kwabenaberko.spotifyui.core.model

import com.benasher44.uuid.uuid4
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.ariana_grande
import spotifyui.common.generated.resources.billie_eilish
import spotifyui.common.generated.resources.bts
import spotifyui.common.generated.resources.drake
import spotifyui.common.generated.resources.dua_lipa
import spotifyui.common.generated.resources.ed_sheeran
import spotifyui.common.generated.resources.fka_twigs
import spotifyui.common.generated.resources.grimes
import spotifyui.common.generated.resources.harry_styles
import spotifyui.common.generated.resources.hayes
import spotifyui.common.generated.resources.hozier
import spotifyui.common.generated.resources.lana_del_rey
import spotifyui.common.generated.resources.led_zeppelin
import spotifyui.common.generated.resources.lolo_zouai
import spotifyui.common.generated.resources.marvin_gaye
import spotifyui.common.generated.resources.one_direction
import spotifyui.common.generated.resources.rihanna
import spotifyui.common.generated.resources.the_weeknd

@OptIn(ExperimentalResourceApi::class)
data class Artist(
    val id: String = uuid4().toString(),
    val name: String,
    val image: DrawableResource? = null,
    val isSelected: Boolean = false,
    val isEditorsPick: Boolean = false
) : Media {
    companion object {
        val billieEilish = Artist(name = "Billie Eilish", image = Res.drawable.billie_eilish)
        val kanyeWest = Artist(name = "Kanye West")
        val arianaGrande = Artist(name = "Ariana Grande", image = Res.drawable.ariana_grande)
        val lanaDelRey = Artist(name = "Lana Del Rey", image = Res.drawable.lana_del_rey)
        val bts = Artist(name = "BTS", image = Res.drawable.bts)
        val drake = Artist(name = "Drake", image = Res.drawable.drake)
        val harryStyles = Artist(name = "Harry Styles", image = Res.drawable.harry_styles)
        val oneDirection = Artist(name = "One Direction", image = Res.drawable.one_direction)
        val rihanna = Artist(name = "Rihanna", image = Res.drawable.rihanna)
        val edSheeran = Artist(name = "Ed Sheeran", image = Res.drawable.ed_sheeran)
        val theWeeknd = Artist(name = "The Weeknd", image = Res.drawable.the_weeknd)
        val duaLipa = Artist(name = "Dua Lipa", image = Res.drawable.dua_lipa)
        val theBeatles = Artist(name = "The Beatles")
        val marvinGaye = Artist(name = "Marvin Gaye", image = Res.drawable.marvin_gaye)
        val fkaTwigs = Artist(name = "FKA twigs", image = Res.drawable.fka_twigs)
        val hozier = Artist(name = "Hozier", image = Res.drawable.hozier)
        val grimes = Artist(name = "Grimes", image = Res.drawable.grimes)
        val hayes = Artist(name = "HAYES", image = Res.drawable.hayes)
        val ledZeppelin = Artist(name = "Led Zeppelin", image = Res.drawable.led_zeppelin)
        val childishGambino = Artist(name = "Childish Gambino")
        val loloZouai = Artist(name = "Lolo Zouaï", image = Res.drawable.lolo_zouai)
    }
}