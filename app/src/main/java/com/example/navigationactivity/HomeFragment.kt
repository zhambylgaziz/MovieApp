package com.example.navigationactivity


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.navigationactivity.adapter.PopularMoviesAdapter
import com.example.navigationactivity.adapter.TopRatedMoviesAdapter
import com.example.navigationactivity.loaders.PopularMoviesLoader
import com.example.navigationactivity.loaders.TopRatedMoviesLoader
import com.example.navigationactivity.model.Movie
import com.example.navigationactivity.model.MovieResponse
import kotlinx.android.synthetic.main.fragment_home.*

const val TYPE_POPULAR = 0
const val TYPE_TOP_RATED = 1
class HomeFragment : Fragment(), MovieLoadListener {
    private val popularMoviesLoader by lazy { PopularMoviesLoader(this) }
    private val topRatedMoviesLoader by lazy { TopRatedMoviesLoader(this) }
    private val popularMoviesAdapter by lazy { PopularMoviesAdapter() }
    private val topRatedMoviesAdapter by lazy { TopRatedMoviesAdapter() }

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
    }
    private fun initUI() {
         with(popular_movies){
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = popularMoviesAdapter
         }
        with(toprated_movies){
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = topRatedMoviesAdapter
        }
    }

    override fun onMoviesLoaded(movies: MovieResponse, type: Int) {
        if(type == TYPE_POPULAR){
            popularMoviesAdapter.setMovies(movies.movies)
        }
        if(type == TYPE_TOP_RATED){
            topRatedMoviesAdapter.setMovies(movies.movies)
        }
    }

    override fun onMoviesLoadError(throwable: Throwable) {
        Toast.makeText(activity, throwable.message, Toast.LENGTH_SHORT).show()
    }

}
