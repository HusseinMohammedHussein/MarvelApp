package com.husseinmohammed.marvelapp.data.restapi

import com.husseinmohammed.marvelapp.data.pojos.CharacterPojo
import retrofit2.Call
import retrofit2.http.GET


// Created by Hussein Mohammed on 9/13/2021.
interface ApiService {

    @GET("v1/public/characters?")
    fun getCharacters(): Call<CharacterPojo>
}