package com.prabitha.kotlin.boommyshowclone.ui


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.prabitha.kotlin.boommyshowclone.ui.adapter.MovieRecyclerView
import com.prabitha.kotlin.boommyshowclone.R
import com.prabitha.kotlin.boommyshowclone.dataremote.remote.MovieRepositoryImpl
import com.prabitha.kotlin.boommyshowclone.dataremote.local.MovieDatabase
import com.prabitha.kotlin.boommyshowclone.dataremote.local.entity.Movie
import com.prabitha.kotlin.boommyshowclone.retrofit.RetroFitBuilder
import com.prabitha.kotlin.boommyshowclone.ui.util.MainViewModelFactory
import com.prabitha.kotlin.boommyshowclone.util.NetworkHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var networkHelper: NetworkHelper
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        networkHelper = NetworkHelper(this)
        showProgressBar()
        setUpViewModel()
        observeViewModel()
    }

    private fun observeViewModel(){

        viewModel.movieResponse.observe(this, {
            hideProgressbar()
            showMovies(it.results)
        })
        viewModel.errorResponse.observe(this, {
            hideProgressbar()
            showErrorMessage(it)
        })

    }
    private fun setUpViewModel() {
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                NetworkHelper(this),
                MovieRepositoryImpl(MovieDatabase.getInstance(this.applicationContext).movieDao(), RetroFitBuilder.buildService())
            )
        )[MainViewModel::class.java]

        viewModel.onCreate()
    }

    private fun showMovies(movies: List<Movie>) {
        recyclerView.adapter = MovieRecyclerView(movies)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showProgressBar(){
        progressBar.visibility= View.VISIBLE
        recyclerView.visibility=View.GONE
        textView5.visibility=View.GONE
    }
    private fun hideProgressbar(){
        progressBar.visibility= View.GONE
        recyclerView.visibility=View.VISIBLE
    }

    private fun showErrorMessage(message:String){
        textView5.text=message
        textView5.visibility=View.VISIBLE
        progressBar.visibility= View.GONE
        recyclerView.visibility=View.GONE
    }
}