package com.husseinmohammed.marvelapp.data.api

import com.husseinmohammed.marvelapp.data.pojos.character.CharacterPojo
import com.husseinmohammed.marvelapp.data.pojos.comic.ComicPojo
import com.husseinmohammed.marvelapp.data.pojos.event.EventPojo
import com.husseinmohammed.marvelapp.data.pojos.series.SeriesPojo
import com.husseinmohammed.marvelapp.data.pojos.story.StoryPojo
import kotlinx.coroutines.flow.Flow


// Created by Hussein Mohammed on 9/18/2021.
interface ApiHelper {

    fun getCharacters(numOfLimit: Int): Flow<CharacterPojo>

    fun getCharacterDetails(characterId: Int): Flow<CharacterPojo>

    fun getCharacterComics(characterId: Int): Flow<ComicPojo>

    fun getCharacterSeries(characterId: Int): Flow<SeriesPojo>

    fun getCharacterStories(characterId: Int): Flow<StoryPojo>

    fun getCharacterEvents(characterId: Int): Flow<EventPojo>

    fun getCharacterSearch(charName: String): Flow<CharacterPojo>
}