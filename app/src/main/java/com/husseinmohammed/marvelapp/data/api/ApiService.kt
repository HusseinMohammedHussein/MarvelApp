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

    @GET("characters")
    suspend fun getCharacters(@Query("limit") numOfLimit: Int): CharacterPojo

    @GET("characters")
    suspend fun getCharacterSearch(@Query("nameStartsWith") charName: String): CharacterPojo

    @GET("characters/{characterId}")
    suspend fun getCharacterDetails(@Path("characterId") characterId: Int): CharacterPojo

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(@Path("characterId") characterId: Int): ComicPojo

    @GET("characters/{characterId}/series")
    suspend fun getCharacterSeries(@Path("characterId") characterId: Int): SeriesPojo

    @GET("characters/{characterId}/stories")
    suspend fun getCharacterStories(@Path("characterId") characterId: Int): StoryPojo

    @GET("characters/{characterId}/events")
    suspend fun getCharacterEvents(@Path("characterId") characterId: Int): EventPojo

}