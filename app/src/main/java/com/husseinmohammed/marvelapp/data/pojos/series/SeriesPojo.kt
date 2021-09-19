package com.husseinmohammed.marvelapp.data.pojos.series


// Created by Hussein Mohammed on 9/16/2021.
data class SeriesPojo(
    val data: SeriesDataPojo
) {
    data class SeriesDataPojo(
        val results: ArrayList<SeriesItemPojo>
    ) {
        data class SeriesItemPojo(
            val title: String,
            val thumbnail: SeriesImagePojo?
        ) {
            data class SeriesImagePojo(
                val path: String,
                val extension: String
            )
        }
    }
}
