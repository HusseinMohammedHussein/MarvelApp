package com.husseinmohammed.marvelapp.data.pojos.api.story

data class StoryPojo(
    val data: StoryDataPojo
) {
    data class StoryDataPojo(
        val results: ArrayList<StoryItemPojo>
    ) {
        data class StoryItemPojo(
            val title: String,
            val thumbnail: StoriesImagePojo?
        ) {
            data class StoriesImagePojo(
                val path: String,
                val extension: String
            )
        }
    }
}