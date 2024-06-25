@file:OptIn(ExperimentalResourceApi::class)

package com.kwabenaberko.spotifyui.core.model

import com.benasher44.uuid.uuid4
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import spotifyui.common.generated.resources.Res
import spotifyui.common.generated.resources.call_her_daddy
import spotifyui.common.generated.resources.distractible
import spotifyui.common.generated.resources.gee_thanks
import spotifyui.common.generated.resources.its_a_lot
import spotifyui.common.generated.resources.last_podcast_on_the_left
import spotifyui.common.generated.resources.my_brother_my_brother_and_me
import spotifyui.common.generated.resources.rotten_mango
import spotifyui.common.generated.resources.the_joe_rogan_experience

sealed class PodcastArtwork {
    @OptIn(ExperimentalResourceApi::class)
    data class Image(val image: DrawableResource) : PodcastArtwork()
    data class Color(val hexColor: String) : PodcastArtwork()

    val isImage: Boolean
        get() {
            return this is Image
        }
}

data class Podcast(
    val id: String = uuid4().toString(),
    val title: String,
    val artwork: PodcastArtwork,
    val isSelected: Boolean = false,
    val isEditorsPick: Boolean = false
) : Media {
    companion object {
        val rottenMango = Podcast(
            title = "Rotten Mango",
            artwork = PodcastArtwork.Image(Res.drawable.rotten_mango)
        )
        val lastPodcastPodcastOnTheLeft = Podcast(
            title = "Last Podcast On The Left",
            artwork = PodcastArtwork.Image(Res.drawable.last_podcast_on_the_left)
        )
        val moreInTrueCrime = Podcast(
            title = "More in True Crime", artwork = PodcastArtwork.Color("#C92D5C")
        )
        val theJoeRoganExperience = Podcast(
            title = "The Joe Rogan Experience",
            artwork = PodcastArtwork.Image(Res.drawable.the_joe_rogan_experience)
        )
        val geeThanks = Podcast(
            title = "Gee Thanks", artwork = PodcastArtwork.Image(Res.drawable.gee_thanks)
        )
        val moreInComedy = Podcast(
            title = "More in Comedy", artwork = PodcastArtwork.Color("#75150D")
        )
        val distractible = Podcast(
            title = "Distractible",
            artwork = PodcastArtwork.Image(Res.drawable.distractible)
        )
        val myBrotherMyBrotherAndMe = Podcast(
            title = "My Brother, My Brother And Me",
            artwork = PodcastArtwork.Image(Res.drawable.my_brother_my_brother_and_me)
        )
        val moreInStories = Podcast(
            title = "More in Stories", artwork = PodcastArtwork.Color("#0D2916")
        )
        val callHerDaddy = Podcast(
            title = "Call Her Daddy",
            artwork = PodcastArtwork.Image(Res.drawable.call_her_daddy)
        )
        val itsALot = Podcast(
            title = "It's A Lot", artwork = PodcastArtwork.Image(Res.drawable.its_a_lot)
        )
        val moreInRelationships = Podcast(
            title = "More in Relationships", artwork = PodcastArtwork.Color("#2C3D60")
        )
    }
}
