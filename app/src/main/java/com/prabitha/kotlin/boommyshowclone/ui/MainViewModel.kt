package com.prabitha.kotlin.boommyshowclone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prabitha.kotlin.boommyshowclone.dataremote.remote.MovieRepository
import com.prabitha.kotlin.boommyshowclone.dataremote.local.entity.MovieResponse
import com.prabitha.kotlin.boommyshowclone.util.NetworkHelper

class MainViewModel(
    private val networkHelper: NetworkHelper,
    private val movieRepository: MovieRepository
) : ViewModel() {
    companion object {
        private const val API_KEY = "7bc0651fe0ea5973822df3bd27e779d9"
    }

    private val _movieResponse = MutableLiveData<MovieResponse>()

    val movieResponse: LiveData<MovieResponse>
        get() = _movieResponse

    private val _errorResponse = MutableLiveData<String>()

    val errorResponse: LiveData<String>
        get() = _errorResponse

    fun onCreate() {

        if (networkHelper.isNetworkConnected()) {
            //have internet fetch it from internet
            movieRepository.fetchMovies(API_KEY, onError = {
                _errorResponse.postValue(it)
            }, onSuccess = {
                _movieResponse.postValue(it)

            })
        } else {
// else fetch it from the database

           movieRepository.getMoviesLocal {
                if(it.results.isNotEmpty()) {
                    _movieResponse.postValue(it)
                }
               else
                {
                    _errorResponse.postValue("Something went wrong")}
           }
        }
    }
}