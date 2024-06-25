package com.kwabenaberko.spotifyui

import androidx.compose.runtime.Composable
import com.kwabenaberko.spotifyui.core.model.Album
import com.kwabenaberko.spotifyui.core.model.Artist
import com.kwabenaberko.spotifyui.core.model.EditorsPick
import com.kwabenaberko.spotifyui.core.model.Genre
import com.kwabenaberko.spotifyui.core.model.Playlist
import com.kwabenaberko.spotifyui.core.model.Podcast
import com.kwabenaberko.spotifyui.core.model.Track

val mockPodcasts = listOf(
    Podcast.rottenMango, Podcast.lastPodcastPodcastOnTheLeft, Podcast.moreInTrueCrime,
    Podcast.theJoeRoganExperience, Podcast.geeThanks, Podcast.moreInComedy,
    Podcast.distractible, Podcast.myBrotherMyBrotherAndMe, Podcast.moreInStories,
    Podcast.callHerDaddy, Podcast.itsALot, Podcast.moreInRelationships,
)

val mockArtists = listOf(
    Artist.billieEilish, Artist.kanyeWest, Artist.arianaGrande, Artist.lanaDelRey,
    Artist.bts, Artist.drake, Artist.harryStyles, Artist.oneDirection, Artist.rihanna,
    Artist.edSheeran, Artist.theWeeknd, Artist.duaLipa,
)

val mockHomeRecentlyPlayed = listOf(
    Album.oneRemastered, Artist.lanaDelRey,
    Artist.marvinGaye, Playlist.indiePop,
    Podcast.rottenMango, Artist.bts, Podcast.theJoeRoganExperience
)

val mockLibraryRecentlyPlayed = listOf(
    Artist.loloZouai, Artist.lanaDelRey,
    Playlist.frontLeft, Artist.marvinGaye,
    Track.les
)

val mockEditorsPicks = listOf(
    EditorsPick(media = Playlist.edSheeranBigSeanJuiceWRLDPostMalone),
    EditorsPick(media = Playlist.mitskiTameImpalaGlassAnimalsCharlieXCX),
    EditorsPick(media = Playlist.todaysTopHits),
    EditorsPick(media = Track.les),
    EditorsPick(media = Artist.fkaTwigs),
)

val mockRecentSearches = listOf(
    Artist.fkaTwigs, Artist.hozier, Artist.grimes, Album.oneRemastered,
    Artist.hayes, Artist.ledZeppelin, Track.les, Playlist.indiePop,
    Podcast.distractible, Podcast.moreInTrueCrime, Artist.billieEilish,
    Podcast.lastPodcastPodcastOnTheLeft, Artist.edSheeran, Podcast.geeThanks,
    Playlist.edSheeranBigSeanJuiceWRLDPostMalone
)

val mockTopGenres = listOf(Genre.pop, Genre.indie)
val mockPopularGenres = listOf(Genre.newsAndPolitics, Genre.comedy)
val mockGenres = listOf(Genre.wrapped2021, Genre.podcasts, Genre.madeForYou, Genre.charts)

@Composable
fun SpotifyUIApp() {
    AppScreen()
}
