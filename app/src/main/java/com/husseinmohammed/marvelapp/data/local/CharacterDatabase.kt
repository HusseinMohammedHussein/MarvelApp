package com.husseinmohammed.marvelapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.husseinmohammed.marvelapp.data.pojos.local.CharacterLocalPojo

// Created by Hussein_Mohammed on 10/2/2021.
@Database(entities = [CharacterLocalPojo::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun charDao(): CharacterDao
}