package com.husseinmohammed.marvelapp.data.local

import com.husseinmohammed.marvelapp.data.pojos.local.CharacterLocalPojo

// Created by Hussein_Mohammed on 10/2/2021.
class DatabaseHelperImpl(private val characterDatabase: CharacterDatabase) : DatabaseHelper {
    override suspend fun insertCharacter(charPojo: List<CharacterLocalPojo>) =
        characterDatabase.charDao().insertCharacters(charPojo)

    override fun getChars(): List<CharacterLocalPojo> = characterDatabase.charDao().getChars()
}