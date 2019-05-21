package com.example.navigationactivity.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navigationactivity.BuildConfig.URL_YOUTUBE
import com.example.navigationactivity.R
import com.example.navigationactivity.model.Video
import kotlinx.android.synthetic.main.video_item.view.*


class VideosAdapter(private val videos: ArrayList<Video> = ArrayList()) : RecyclerView.Adapter<VideosAdapter.ViewHolder>(){

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosAdapter.ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.video_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovie(videos[position])
    }

    override fun getItemCount() = videos.size

    fun setVideos(data: List<Video>) {
        videos.clear()
        videos.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindMovie(video: Video) {
            with(view) {
                video_name.text = video.name
//                val mediaController = MediaController(this)
//                mediaController.setAnchorView(movie_video)
                val uri = Uri.parse(URL_YOUTUBE + video.key)
//                movie_video.setMediaController(mediaController)
                movie_video.setVideoURI(uri)
                movie_video.requestFocus()
                movie_video.start()
            }
        }
    }
}

