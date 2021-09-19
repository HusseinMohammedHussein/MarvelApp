package com.husseinmohammed.marvelapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.husseinmohammed.marvelapp.data.api.ApiHelper
import com.husseinmohammed.marvelapp.ui.fragments.characterdetails.CharacterDetailsViewModel
import com.husseinmohammed.marvelapp.ui.fragments.characters.CharactersViewModel
import com.husseinmohammed.marvelapp.ui.fragments.chractersearch.CharactersSearchViewModel


// Created by Hussein Mohammed on 9/18/2021.
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) return CharactersViewModel(apiHelper) as T

        if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) return CharacterDetailsViewModel(apiHelper) as T

        if (modelClass.isAssignableFrom(CharactersSearchViewModel::class.java)) return CharactersSearchViewModel(apiHelper) as T

        throw IllegalArgumentException("Unknown class name")
    }
}