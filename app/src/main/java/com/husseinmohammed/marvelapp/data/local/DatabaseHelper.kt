package com.husseinmohammed.marvelapp.data.local

import com.husseinmohammed.marvelapp.data.pojos.local.CharacterLocalPojo

// Created by Hussein_Mohammed on 10/2/2021.
interface DatabaseHelper {

    suspend fun insertCharacter(charPojo: List<CharacterLocalPojo>)

    fun getChars(): List<CharacterLocalPojo>
}