package com.prabitha.kotlin.boommyshowclone.dataremote.remote

import com.prabitha.kotlin.boommyshowclone.dataremote.local.dao.MovieDao
import com.prabitha.kotlin.boommyshowclone.dataremote.local.entity.MovieResponse
import com.prabitha.kotlin.boommyshowclone.retrofit.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieDAo: MovieDao,
    val request: MovieService
) : MovieRepository {
    override fun fetchMovies(

        api_key: String,
        onError: (String) -> Unit,
        onSuccess: (MovieResponse) -> Unit
    ) {

        val call = request.getMovies(api_key)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    //success
                    Thread {

                        movieDAo.insertMovies(response.body()!!)
                        onSuccess(response.body()!!)
                    }.start()

                } else {
                    onError(response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onError("Oops something happened")
            }

        })

    }

    override fun getMoviesLocal(onSuccess: (MovieResponse) -> Unit) {
        Thread {

            onSuccess(movieDAo.getMovies())
        }.start()
    }
}