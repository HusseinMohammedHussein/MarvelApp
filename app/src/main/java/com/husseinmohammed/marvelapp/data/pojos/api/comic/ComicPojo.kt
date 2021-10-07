package com.husseinmohammed.marvelapp.data.pojos.api.comic


// Created by Hussein Mohammed on 9/15/2021.
data class ComicPojo(
    val data: ComicsDataPojo
) {
    data class ComicsDataPojo(
        val results: ArrayList<ComicItemsPojo>
    )

    data class ComicItemsPojo(
        val title: String,
        val thumbnail: ComicImagePojo?
    ) {
        data class ComicImagePojo(
            val path: String, val extension: String
        )
    }
}
