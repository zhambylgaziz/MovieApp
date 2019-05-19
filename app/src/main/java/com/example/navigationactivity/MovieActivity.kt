package com.example.navigationactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.navigationactivity.adapter.RecommendedMoviesAdapter
import com.example.navigationactivity.loaders.MovieLoader
import com.example.navigationactivity.loaders.RecommendedMoviesLoader
import com.example.navigationactivity.loaders.TYPE_RECOMMENDED
import com.example.navigationactivity.model.Movie
import com.example.navigationactivity.model.MovieResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity(), MoviesLoadListener, MovieLoadListener {

    private val recommendedMoviesAdapter by lazy { RecommendedMoviesAdapter() }
    private val recommendedMoviesLoader by lazy { RecommendedMoviesLoader(this) }
    private val movieLoader by lazy { MovieLoader(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        movieLoader.loadMovie(intent.getStringExtra("id"))
        initUI()
    }

    private fun initUI() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        with(recomended_movies){
            layoutManager = android.support.v7.widget.LinearLayoutManager(
                context,
                android.support.v7.widget.OrientationHelper.HORIZONTAL,
                false
            )
            adapter = recommendedMoviesAdapter
        }
    }

    override fun onMoviesLoaded(movies: MovieResponse, type: Int) {
        if(type == TYPE_RECOMMENDED){
            recommendedMoviesAdapter.setMovies(movies.movies)
        }
    }

    override fun onMoviesLoadError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onMovieLoaded(movie: Movie) {
        supportActionBar!!.setTitle(movie.title)
        recommendedMoviesLoader.loadMovies(movie)
        Picasso.get().load(BuildConfig.URL_POSTER + movie.poster).into(poster_movie)
        movie_title.text = movie.title
        movie_overview.text = movie.overview

    }

    override fun onMovieLoadError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
