package com.husseinmohammed.marvelapp.ui.fragments.characters

import com.husseinmohammed.marvelapp.data.pojos.CharacterPojo
import com.husseinmohammed.marvelapp.data.restapi.retrofit.ApiClient
import retrofit2.Call


// Created by Hussein Mohammed on 9/13/2021.
class CharactersRepo {
    fun getCharacters(): Call<CharacterPojo> {
        return ApiClient.apiServer().getCharacters()
    }
}