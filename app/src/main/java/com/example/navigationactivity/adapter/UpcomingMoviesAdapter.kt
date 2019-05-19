package com.example.navigationactivity.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationactivity.BuildConfig.URL_POSTER
import com.example.navigationactivity.MovieClickListener
import com.example.navigationactivity.R
import com.example.navigationactivity.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.raw_movies.view.*

class UpcomingMoviesAdapter(private val movies: ArrayList<Movie> = ArrayList()) : RecyclerView.Adapter<UpcomingMoviesAdapter.ViewHolder>(){

    private lateinit var movieClickListener: MovieClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesAdapter.ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.raw_movies, parent, false))

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
                Picasso.get().load(URL_POSTER + movie.poster).into(poster)
                description.text = movie.title

                setOnClickListener {
                    movieClickListener.onMovieClicked(movie)
                }
            }
        }
    }
}

