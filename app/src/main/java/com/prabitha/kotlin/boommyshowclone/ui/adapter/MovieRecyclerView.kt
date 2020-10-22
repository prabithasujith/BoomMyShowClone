package com.prabitha.kotlin.boommyshowclone.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabitha.kotlin.boommyshowclone.R
import com.prabitha.kotlin.boommyshowclone.dataremote.local.entity.Movie
import kotlinx.android.synthetic.main.movie_card_item.view.*

class MovieRecyclerView(val movies:List<Movie>) : RecyclerView.Adapter<MovieRecyclerView.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_card_item, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.count()


    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            private const val BASE_URL: String = "https://image.tmdb.org/t/p/w500"
        }

        fun bind(movie: Movie) {
            itemView.textView.text = movie.title
            itemView.textView2.text = movie.voteCount.toString()
            itemView.textView3.text = movie.releaseDate
            itemView.textView4.text= movie.voteAverage.toString()
            Glide.with(itemView.context).load(BASE_URL + movie.posterPath).into(itemView.imageView2)
        }
    }
}