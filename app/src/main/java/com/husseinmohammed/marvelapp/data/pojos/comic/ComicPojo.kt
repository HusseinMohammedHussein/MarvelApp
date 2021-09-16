package com.husseinmohammed.marvelapp.data.pojos.comic


// Created by Hussein Mohammed on 9/15/2021.
data class ComicPojo(
    val data: ComicsDataPojo
) {
    data class ComicsDataPojo(
        val results: ArrayList<ComicItemsPojo>
    )

    data class ComicItemsPojo(
        val thumbnail: ComicImagePojo,
        val title: String
    ) {
        data class ComicImagePojo(
            val path: String, val extension: String
        )
    }
}
