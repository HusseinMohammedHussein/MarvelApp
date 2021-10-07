package com.husseinmohammed.marvelapp.data.local

import android.content.Context
import androidx.room.Room

// Created by Hussein_Mohammed on 10/2/2021.
object DatabaseBuilder {

    private var INSTANCE: CharacterDatabase? = null

    fun getInstance(context: Context): CharacterDatabase {
        if (INSTANCE == null) {
            synchronized(CharacterDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            CharacterDatabase::class.java,
            "char-db"
        ).allowMainThreadQueries().build()
}