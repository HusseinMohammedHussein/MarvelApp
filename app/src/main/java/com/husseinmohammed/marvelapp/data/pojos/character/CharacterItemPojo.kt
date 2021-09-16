package com.husseinmohammed.marvelapp.data.pojos.character

import com.husseinmohammed.marvelapp.data.pojos.comic.ComicPojo


// Created by Hussein Mohammed on 9/15/2021.
data class CharacterItemPojo(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ImagePojo,
) {
    class ImagePojo(
        val path: String, val extension: String,
    )
}

