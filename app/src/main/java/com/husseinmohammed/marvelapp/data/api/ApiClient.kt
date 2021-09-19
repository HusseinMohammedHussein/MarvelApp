package com.husseinmohammed.marvelapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


// Created by Hussein Mohammed on 9/13/2021.
object ApiClient {
    private const val BASE_URL =
        "https://gateway.marvel.com:443/"
    private var okHttpClient: OkHttpClient = OkHttpClient()
    private const val REQUEST_TIMEOUT: Int = 60

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(initOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initOkHttp(): OkHttpClient {
        okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(HeaderInterceptor())
            addInterceptor(HttpLoggingInterceptor { message ->
                Timber.i("Data::${message}")
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
        }.build()
        return okHttpClient
    }

    val apiServer: ApiService = getRetrofit().create(ApiService::class.java)
}