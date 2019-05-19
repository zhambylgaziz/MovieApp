package com.example.navigationactivity.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.navigationactivity.*
import com.example.navigationactivity.adapter.GenresAdapter
import com.example.navigationactivity.adapter.MovieByGenresAdapter
import com.example.navigationactivity.loaders.GenresLoader
import com.example.navigationactivity.loaders.MoviesByGenresLoader
import com.example.navigationactivity.model.Genre
import com.example.navigationactivity.model.GenreResponse
import com.example.navigationactivity.model.Movie
import com.example.navigationactivity.model.MovieResponse
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment(), GenresLoadListener, GenreClickListener, MoviesLoadListener, MovieClickListener  {

    private val genresLoader by lazy { GenresLoader(this) }
    private val genresAdapter by lazy { GenresAdapter() }
    private val moviesByGenresLoader by lazy { MoviesByGenresLoader(this) }
    private val movieByGenresAdapter by lazy { MovieByGenresAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_movies, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        genresLoader.loadGenres()
    }
    private fun initUI() {
        genresAdapter.setListener(this)
        movieByGenresAdapter.setListener(this)
        with(genres_list){
            layoutManager = LinearLayoutManager(context)
            adapter = genresAdapter
        }
        with(movies_recyclerView){
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = movieByGenresAdapter
        }
    }
    override fun onGenresLoaded(genres: GenreResponse) {
        genresAdapter.setGenres(genres.genres)
    }

    override fun onGenresLoadError(throwable: Throwable) {
        Toast.makeText(activity, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onGenreClicked(genre: Genre) {
        moviesByGenresLoader.loadMovies(genre.id.toString())
    }

    override fun onMoviesLoaded(movies: MovieResponse, type: Int) {
        movieByGenresAdapter.setMovies(movies.movies)
    }

    override fun onMoviesLoadError(throwable: Throwable) {
        Toast.makeText(activity, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onMovieClicked(movie: Movie) {
        Toast.makeText(activity, movie.title, Toast.LENGTH_SHORT).show()
    }
}
