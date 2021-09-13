package com.husseinmohammed.marvelapp.data.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


// Created by Hussein Mohammed on 9/13/2021.
data class CharacterPojo(
    val status: String, val code: Int,
    @SerializedName("data") @Expose val characterData: CharacterDataPojo
) {

    data class CharacterDataPojo(
        @SerializedName("results") @Expose val characterItems: ArrayList<CharacterItemPojo>
    ) {

        data class CharacterItemPojo(
            val id: String,
            val name: String,
            val thumbnail: ImagePojo
        ) {
            class ImagePojo(
                val path: String, val extension: String
            )

        }
    }
}
