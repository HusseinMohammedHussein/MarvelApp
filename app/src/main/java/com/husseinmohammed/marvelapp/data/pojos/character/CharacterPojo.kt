package com.husseinmohammed.marvelapp.data.pojos.character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.husseinmohammed.marvelapp.data.pojos.ResponseStatusPojo


// Created by Hussein Mohammed on 9/13/2021.
data class CharacterPojo(
    @SerializedName("data") @Expose val characterData: CharacterDataPojo
) : ResponseStatusPojo() {

    data class CharacterDataPojo(
        @SerializedName("results") @Expose val characterResults: ArrayList<CharacterItemPojo>,

    )
}
