package com.husseinmohammed.marvelapp.data.pojos.api.character

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// Created by Hussein Mohammed on 9/15/2021.
@Parcelize
data class CharacterItemPojo(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ImagePojo?,
) : Parcelable {

    @Parcelize
    data class ImagePojo(
        val path: String, val extension: String,
    ) : Parcelable
}

