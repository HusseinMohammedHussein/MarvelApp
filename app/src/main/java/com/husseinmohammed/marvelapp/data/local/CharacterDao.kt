package com.husseinmohammed.marvelapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.husseinmohammed.marvelapp.data.pojos.local.CharacterLocalPojo

// Created by Hussein_Mohammed on 10/2/2021.
@Dao
interface CharacterDao {
    @Insert
    suspend fun insertCharacters(chars: List<CharacterLocalPojo>)

    @Query("SELECT * FROM Character")
    fun getChars(): List<CharacterLocalPojo>
}