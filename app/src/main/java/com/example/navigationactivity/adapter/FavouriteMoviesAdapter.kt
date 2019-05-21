package com.example.navigationactivity.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationactivity.BuildConfig.URL_POSTER
import com.example.navigationactivity.listener.MovieClickListener
import com.example.navigationactivity.R
import com.example.navigationactivity.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.favourite_movie_item.view.*

class FavouriteMoviesAdapter(private val movies: ArrayList<Movie> = ArrayList()) : RecyclerView.Adapter<FavouriteMoviesAdapter.ViewHolder>(){

    private lateinit var movieClickListener: MovieClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteMoviesAdapter.ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.favourite_movie_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovie(movies[position])
    }

    override fun getItemCount() = movies.size

    fun setMovies(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }

    fun setListener(listener: MovieClickListener) {
        movieClickListener = listener
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindMovie(movie: Movie) {
            with(view) {
                Picasso.get().load(URL_POSTER + movie.poster).into(fav_movie_poster)
                fav_movie_title.text = movie.title
                fav_movie_overview.text = movie.overview

                setOnClickListener {
                    movieClickListener.onMovieClicked(movie)
                }
            }
        }
    }
}

