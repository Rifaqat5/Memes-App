package com.example.memeapp

import retrofit2.Call
import retrofit2.http.GET

interface Api_Endt {

    @GET("gimme")
    fun get_all_meme() : Call<MyResponse>
}