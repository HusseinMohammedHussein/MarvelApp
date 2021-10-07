package com.husseinmohammed.marvelapp.data.pojos.api.event

data class EventPojo(
    val data: EventsDataPojo
) {
    data class EventsDataPojo(
        val results: ArrayList<EventItemPojo>
    ) {
        data class EventItemPojo(
            val title: String?,
            val thumbnail: EventImagePojo?
        ) {
            data class EventImagePojo(
                val path: String,
                val extension: String
            )
        }
    }
}
