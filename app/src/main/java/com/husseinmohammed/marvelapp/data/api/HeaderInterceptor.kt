package com.husseinmohammed.marvelapp.data.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.math.BigInteger
import java.security.MessageDigest


// Created by Hussein Mohammed on 9/13/2021.
class HeaderInterceptor : Interceptor {

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val publicKey = "fb108f3f8e6aa742db698022a82912e4"
        val privateKey = "eeb8e240014ec78a5f7a18c166a039fce86df919"
        val ts = System.currentTimeMillis().toString()
        val stringToHash = "$ts$privateKey$publicKey"
        Timber.d("StringToHash::$stringToHash")

        val hash = md5(stringToHash)

        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        val httpUrl: HttpUrl = request.url
        val httpUrlBuilder: HttpUrl.Builder = httpUrl.newBuilder()

        requestBuilder.addHeader("Content-Type", "application/json")

        httpUrlBuilder.addQueryParameter("ts", ts)
        httpUrlBuilder.addQueryParameter("apikey", publicKey)
        httpUrlBuilder.addQueryParameter("hash", hash)

        Timber.d("HeaderInterceptor:::ts::$ts, apiKey::$publicKey, hash::$hash")

        requestBuilder.url(httpUrlBuilder.build())
        return chain.proceed(requestBuilder.build())
    }
}