package com.prabitha.kotlin.boommyshowclone.dataremote.remote

import com.prabitha.kotlin.boommyshowclone.dataremote.local.entity.MovieResponse

interface MovieRepository {
    fun fetchMovies(api_key: String, onError: (String) -> Unit, onSuccess: (MovieResponse) -> Unit)

    fun getMoviesLocal(onSuccess: (MovieResponse) -> Unit)
}