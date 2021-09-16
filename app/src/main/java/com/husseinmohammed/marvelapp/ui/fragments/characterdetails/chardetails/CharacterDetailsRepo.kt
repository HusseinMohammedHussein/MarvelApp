package com.husseinmohammed.marvelapp.ui.fragments.characterdetails.chardetails

import com.husseinmohammed.marvelapp.data.pojos.character.CharacterPojo
import com.husseinmohammed.marvelapp.data.pojos.comic.ComicPojo
import com.husseinmohammed.marvelapp.data.pojos.series.SeriesPojo
import com.husseinmohammed.marvelapp.data.restapi.retrofit.ApiClient
import retrofit2.Call


// Created by Hussein Mohammed on 9/15/2021.
class CharacterDetailsRepo {
    fun getCharacterDetails(charId: Int): Call<CharacterPojo> {
        return ApiClient.apiServer().getCharacterDetails(charId)
    }

    fun getCharacterComics(charId: Int): Call<ComicPojo> {
        return ApiClient.apiServer().getCharacterComics(charId)
    }

    fun getCharacterSeries(charId: Int): Call<SeriesPojo> {
        return ApiClient.apiServer().getCharacterSeries(charId)
    }
}