package com.husseinmohammed.marvelapp.data.restapi

import com.husseinmohammed.marvelapp.data.pojos.character.CharacterPojo
import com.husseinmohammed.marvelapp.data.pojos.comic.ComicPojo
import com.husseinmohammed.marvelapp.data.pojos.series.SeriesPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


// Created by Hussein Mohammed on 9/13/2021.
interface ApiService {

    @GET("v1/public/characters?")
    fun getCharacters(): Call<CharacterPojo>

    @GET("v1/public/characters/{characterId}?")
    fun getCharacterDetails(@Path("characterId") characterId: Int): Call<CharacterPojo>

    @GET("v1/public/characters/{characterId}/comics?")
    fun getCharacterComics(@Path("characterId") characterId: Int): Call<ComicPojo>

    @GET("v1/public/characters/{characterId}/series?")
    fun getCharacterSeries(@Path("characterId") characterId: Int): Call<SeriesPojo>

}