package com.husseinmohammed.marvelapp.data.api

import com.husseinmohammed.marvelapp.data.pojos.character.CharacterPojo
import com.husseinmohammed.marvelapp.data.pojos.comic.ComicPojo
import com.husseinmohammed.marvelapp.data.pojos.event.EventPojo
import com.husseinmohammed.marvelapp.data.pojos.series.SeriesPojo
import com.husseinmohammed.marvelapp.data.pojos.story.StoryPojo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


// Created by Hussein Mohammed on 9/13/2021.
interface ApiService {

    @GET("v1/public/characters?")
    suspend fun getCharacters(@Query("limit") numOfLimit: Int): CharacterPojo

    @GET("v1/public/characters?")
    suspend fun getCharacterSearch(@Query("nameStartsWith") charName: String): CharacterPojo

    @GET("v1/public/characters/{characterId}?")
    suspend fun getCharacterDetails(@Path("characterId") characterId: Int): CharacterPojo

    @GET("v1/public/characters/{characterId}/comics?")
    suspend fun getCharacterComics(@Path("characterId") characterId: Int): ComicPojo

    @GET("v1/public/characters/{characterId}/series?")
    suspend fun getCharacterSeries(@Path("characterId") characterId: Int): SeriesPojo

    @GET("v1/public/characters/{characterId}/stories?")
    suspend fun getCharacterStories(@Path("characterId") characterId: Int): StoryPojo

    @GET("v1/public/characters/{characterId}/events?")
    suspend fun getCharacterEvents(@Path("characterId") characterId: Int): EventPojo

}