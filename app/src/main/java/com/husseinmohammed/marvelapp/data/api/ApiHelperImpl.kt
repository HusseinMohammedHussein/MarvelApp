package com.husseinmohammed.marvelapp.data.api

import kotlinx.coroutines.flow.flow


// Created by Hussein Mohammed on 9/18/2021.

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override fun getCharacters(numOfLimit: Int) =
        flow { emit(apiService.getCharacters(numOfLimit)) }

    override fun getCharacterDetails(characterId: Int) =
        flow { emit(apiService.getCharacterDetails(characterId)) }

    override fun getCharacterComics(characterId: Int) =
        flow { emit(apiService.getCharacterComics(characterId)) }

    override fun getCharacterSeries(characterId: Int) =
        flow { emit(apiService.getCharacterSeries(characterId)) }

    override fun getCharacterStories(characterId: Int) =
        flow { emit(apiService.getCharacterStories(characterId)) }

    override fun getCharacterEvents(characterId: Int) =
        flow { emit(apiService.getCharacterEvents(characterId)) }

    override fun getCharacterSearch(charName: String) =
        flow { emit(apiService.getCharacterSearch(charName)) }
}