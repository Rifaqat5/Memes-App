package com.example.memeapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientApi {


    var RETROFIT: Retrofit? = null

    fun getClient() : Retrofit{

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        var gson: Gson = GsonBuilder().create()
        if (RETROFIT == null) {


            RETROFIT = Retrofit.Builder().baseUrl("https://meme-api.com/").client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
        }

        return RETROFIT!!
    }
}