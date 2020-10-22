package com.prabitha.kotlin.boommyshowclone.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitBuilder{
    private  const val BASE_URL  :String="https://api.themoviedb.org/3/"
    private val gson= GsonBuilder().create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    fun buildService(): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}