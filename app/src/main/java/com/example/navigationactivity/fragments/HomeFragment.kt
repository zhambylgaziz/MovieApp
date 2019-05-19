package com.example.navigationactivity.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.navigationactivity.MovieActivity
import com.example.navigationactivity.MovieClickListener
import com.example.navigationactivity.MoviesLoadListener
import com.example.navigationactivity.R
import com.example.navigationactivity.adapter.NowPlayingMoviesAdapter
import com.example.navigationactivity.adapter.PopularMoviesAdapter
import com.example.navigationactivity.adapter.TopRatedMoviesAdapter
import com.example.navigationactivity.adapter.UpcomingMoviesAdapter
import com.example.navigationactivity.loaders.*
import com.example.navigationactivity.model.Movie
import com.example.navigationactivity.model.MovieResponse
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), MoviesLoadListener,
    MovieClickListener {
    private val popularMoviesLoader by lazy { PopularMoviesLoader(this) }
    private val topRatedMoviesLoader by lazy { TopRatedMoviesLoader(this) }
    private val upcomingMoviesLoader by lazy { UpcomingMoviesLoader(this) }
    private val nowPlayingMoviesLoader by lazy { NowPlayingMoviesLoader(this) }
    private val popularMoviesAdapter by lazy { PopularMoviesAdapter() }
    private val topRatedMoviesAdapter by lazy { TopRatedMoviesAdapter() }
    private val upcomingMoviesAdapter by lazy { UpcomingMoviesAdapter() }
    private val nowPlayingMoviesAdapter by lazy { NowPlayingMoviesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        popularMoviesLoader.loadMovies()
        topRatedMoviesLoader.loadMovies()
        upcomingMoviesLoader.loadMovies()
        nowPlayingMoviesLoader.loadMovies()
    }
    private fun initUI() {

        popularMoviesAdapter.setListener(this)
        topRatedMoviesAdapter.setListener(this)
        upcomingMoviesAdapter.setListener(this)

        with(popular_movies){
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = popularMoviesAdapter
        }
        with(toprated_movies){
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = topRatedMoviesAdapter
        }
        with(upcoming_movies){
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = upcomingMoviesAdapter
        }
        with(nowPlaying_movies){
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = nowPlayingMoviesAdapter
        }
    }

    override fun onMoviesLoaded(movies: MovieResponse, type: Int) {
        if(type == TYPE_POPULAR){
            popularMoviesAdapter.setMovies(movies.movies)
        }
        if(type == TYPE_TOP_RATED){
            topRatedMoviesAdapter.setMovies(movies.movies)
        }
        if(type == TYPE_UPCOMING){
            upcomingMoviesAdapter.setMovies(movies.movies)
        }
        if(type == TYPE_NOW_PLAYING){
            upcomingMoviesAdapter.setMovies(movies.movies)
        }
    }

    override fun onMoviesLoadError(throwable: Throwable) {
        Toast.makeText(activity, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onMovieClicked(movie: Movie) {
        val intent = Intent(activity, MovieActivity::class.java)
        intent.putExtra("id", movie.id.toString())
        startActivity(intent)
    }

}
