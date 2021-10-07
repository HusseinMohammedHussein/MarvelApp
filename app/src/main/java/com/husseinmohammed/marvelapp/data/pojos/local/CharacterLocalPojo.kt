package com.husseinmohammed.marvelapp.data.pojos.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// Created by Hussein_Mohammed on 10/2/2021.
@Entity(tableName = "Character")
@Parcelize
data class CharacterLocalPojo(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "char_name")
    val name: String,
    @ColumnInfo(name = "char_image")
    val thumbnail: String,
) : Parcelable
