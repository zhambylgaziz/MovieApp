package com.example.navigationactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.MenuItem
import android.widget.Toast
import com.example.navigationactivity.adapter.*
import com.example.navigationactivity.fragments.MOVIES_COLLECTION
import com.example.navigationactivity.listener.*
import com.example.navigationactivity.loaders.*
import com.example.navigationactivity.model.CastResponse
import com.example.navigationactivity.model.Movie
import com.example.navigationactivity.model.MovieResponse
import com.example.navigationactivity.model.VideoResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity(), MoviesLoadListener, VideoLoadListener,
    MovieLoadListener,CastLoadListener, MovieClickListener {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val firebaseCloudstore by lazy { FirebaseFirestore.getInstance() }
    private val moviesCollection by lazy { firebaseCloudstore.collection(MOVIES_COLLECTION) }
    private val recommendedMoviesAdapter by lazy { RecommendedMoviesAdapter() }
    private val recommendedMoviesLoader by lazy { RecommendedMoviesLoader(this) }
    private val movieLoader by lazy { MovieLoader(this) }
    private val productionCompaniesLogoAdapter by lazy { ProductionCompaniesLogoAdapter() }
    private val castLoader by lazy { CastLoader(this)}
    private val castAdapter by lazy {CastAdapter()}
    private val similarMoviesAdapter by lazy { SimilarMoviesAdapter()}
    private val similarMoviesLoader by lazy { SimilarMoviesLoader(this) }
    private val videoLoader by lazy { VideoLoader(this)}
    private val videosAdapter by lazy { VideosAdapter() }

    private var isHave = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        movieLoader.loadMovie(intent.getStringExtra("id"))
        initUI()
    }
    private fun initUI() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        loadData()
        with(recomended_movies){
            layoutManager = android.support.v7.widget.LinearLayoutManager(
                context,
                android.support.v7.widget.OrientationHelper.HORIZONTAL,
                false
            )
            adapter = recommendedMoviesAdapter
        }
        with(companies_logo){
            layoutManager = android.support.v7.widget.LinearLayoutManager(
                context,
                android.support.v7.widget.OrientationHelper.HORIZONTAL,
                false
            )
            adapter = productionCompaniesLogoAdapter
        }
        with(cast_recyclerview){
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = castAdapter
        }
        with(similar_movies){
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = similarMoviesAdapter
        }
        with(videos_recyclerview){
            layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
            adapter = videosAdapter
        }
        add_button.setOnClickListener{addOrRemoveMovie()}
    }
    private fun loadData() {
        val user = firebaseAuth.currentUser
        if (user != null) {
            moviesCollection.whereEqualTo("user", user.email.toString())
                .whereEqualTo("id", intent.getStringExtra("id"))
                .get()
                .addOnCompleteListener{ task->
                    if(task.isSuccessful){
                        if(task.result!!.isEmpty){
                            add_button.text = getString(R.string.add_to_favourites_label)
                            isHave = false
                        }else{
                            add_button.text = getString(R.string.remove_label)
                            isHave = true
                        }
                    }
                }

        }
    }
    private fun addOrRemoveMovie(){
        val user = firebaseAuth.currentUser
        if (user != null) {
            val data = HashMap<String, Any>()
            data["id"] = intent.getStringExtra("id")
            data["user"] = user.email.toString()
            if(!isHave){
                moviesCollection.add(data).addOnCompleteListener{task ->
                    run{
                        if(task.isSuccessful){
                            Toast.makeText(this, "Successfully added to favourites", Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }else{
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Sign in first", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onMoviesLoaded(movies: MovieResponse, type: Int) {
        if(type == TYPE_RECOMMENDED){
            recommendedMoviesAdapter.setMovies(movies.movies)
        }
        if(type == TYPE_SIMILAR){
            similarMoviesAdapter.setMovies(movies.movies)
        }
    }

    override fun onMoviesLoadError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onMovieLoaded(movie: Movie) {
        supportActionBar!!.setTitle(movie.title)
        recommendedMoviesLoader.loadMovies(movie)
        similarMoviesLoader.loadMovies(movie)
        castLoader.loadCast(movie.id.toString())
        productionCompaniesLogoAdapter.setCompanies(movie.productionCompanies)
        videoLoader.loadVideos(movie.id.toString())
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

    override fun onCastLoaded(casts: CastResponse) {
        castAdapter.setCast(casts.cast)
    }

    override fun onCastLoadError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onMovieClicked(movie: Movie) {
        Toast.makeText(this, movie.title, Toast.LENGTH_SHORT).show()
    }
    override fun onVideoLoaded(videos: VideoResponse) {
        videosAdapter.setVideos(videos.videos)
    }

    override fun onVideoLoadError(throwable: Throwable) {
       Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
}
