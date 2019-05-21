package com.example.navigationactivity.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationactivity.BuildConfig
import com.example.navigationactivity.R
import com.example.navigationactivity.model.Cast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cast_item.view.*


class CastAdapter(private val casts: ArrayList<Cast> = ArrayList()) : RecyclerView.Adapter<CastAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.cast_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCast(casts[position])
    }

    override fun getItemCount() = casts.size

    fun setCast(data: List<Cast>) {
        casts.clear()
        casts.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindCast (cast: Cast) {
            with(view) {
                Picasso.get().load(BuildConfig.URL_POSTER + cast.profile_path).into(profile)
                name_textview.text = cast.name
                character_name.text = cast.character

            }
        }
    }
}


