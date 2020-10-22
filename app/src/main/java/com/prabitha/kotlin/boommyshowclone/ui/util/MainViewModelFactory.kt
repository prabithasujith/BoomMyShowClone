package com.prabitha.kotlin.boommyshowclone.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prabitha.kotlin.boommyshowclone.dataremote.remote.MovieRepository
import com.prabitha.kotlin.boommyshowclone.ui.MainViewModel
import com.prabitha.kotlin.boommyshowclone.util.NetworkHelper

class MainViewModelFactory(
    private val networkHelper: NetworkHelper,
    private val movieRepository: MovieRepository
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(networkHelper,movieRepository) as T
    }
}
