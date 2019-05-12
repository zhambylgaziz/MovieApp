package com.example.navigationactivity.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationactivity.R
import kotlinx.android.synthetic.main.raw_movies.view.*

class PopularAdapter(private val movies: ArrayList<String>) : RecyclerView.Adapter<PopularAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.raw_movies, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovie(movies[position])
    }

    override fun getItemCount() = movies.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindMovie(movie: String) {
            itemView.poster.setImageResource(R.drawable.ic_launcher_foreground)
            itemView.description.text = movie
        }
    }
}

