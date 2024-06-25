package com.kwabenaberko.spotifyui.core.model

sealed interface Media

val Media.id: String
    get() {
        return when (this) {
            is Track -> this.id
            is Artist -> this.id
            is Album -> this.id
            is Playlist -> this.id
            is Podcast -> this.id
        }
    }

val Media.isEditorsPick: Boolean
    get() {
        return when (this) {
            is Track -> this.isEditorsPick
            is Artist -> this.isEditorsPick
            is Album -> this.isEditorsPick
            is Playlist -> this.isEditorsPick
            is Podcast -> this.isEditorsPick
        }
    }