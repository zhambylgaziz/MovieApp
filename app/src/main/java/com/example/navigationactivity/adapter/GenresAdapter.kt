package com.example.navigationactivity.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationactivity.listener.GenreClickListener
import com.example.navigationactivity.R
import com.example.navigationactivity.model.Genre
import kotlinx.android.synthetic.main.list_genres.view.*


class GenresAdapter(private val genres: ArrayList<Genre> = ArrayList()) : RecyclerView.Adapter<GenresAdapter.ViewHolder>(){

    private lateinit var genreClickListener: GenreClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresAdapter.ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.list_genres, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindGenre(genres[position])
    }

    override fun getItemCount() = genres.size

    fun setGenres(data: List<Genre>) {
        genres.clear()
        genres.addAll(data)
        notifyDataSetChanged()
    }

    fun setListener(listener: GenreClickListener) {
        genreClickListener = listener
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindGenre (genre: Genre) {
            with(view) {
                genre_name.text = genre.name
                setOnClickListener {
                    genreClickListener.onGenreClicked(genre)
                }

            }
        }
    }
}


