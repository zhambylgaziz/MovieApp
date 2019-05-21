package com.example.navigationactivity.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.navigationactivity.MovieActivity
import com.example.navigationactivity.R
import com.example.navigationactivity.adapter.FavouriteMoviesAdapter
import com.example.navigationactivity.listener.MovieClickListener
import com.example.navigationactivity.listener.MovieLoadListener
import com.example.navigationactivity.loaders.MovieLoader
import com.example.navigationactivity.model.Movie
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.android.synthetic.main.fragment_login.*
const val MOVIES_COLLECTION = "movies"
class FavouritesFragment : Fragment(), MovieLoadListener, MovieClickListener {
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val firebaseCloudstore by lazy { FirebaseFirestore.getInstance() }
    private val moviesCollection by lazy { firebaseCloudstore.collection(MOVIES_COLLECTION) }
    private val movieLoader by lazy { MovieLoader(this) }
    private val favouriteMoviesAdapter by lazy { FavouriteMoviesAdapter() }
    private val movies = ArrayList<Movie>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun loadData() {
        moviesCollection.whereEqualTo("user", firebaseAuth.currentUser?.email)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    result!!.forEach { document ->
                        movieLoader.loadMovie(document.getString("id"))
                    }
                }
            }
    }

    fun initUI(){
        sign_out_button.setOnClickListener{signOut()}
        loadData()
        with(favourite_movies){
            layoutManager = LinearLayoutManager(activity)
            adapter = favouriteMoviesAdapter
        }
    }

    private fun signOut(){
        firebaseAuth.signOut()

    }
    override fun onMovieLoaded(movie: Movie) {
        movies.add(movie)
        favouriteMoviesAdapter.setMovies(movies)
    }

    override fun onMovieLoadError(throwable: Throwable) {
        Toast.makeText(activity, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onMovieClicked(movie: Movie) {
        val intent = Intent(activity, MovieActivity::class.java)
        intent.putExtra("id", movie.id.toString())
        startActivity(intent)
    }

}
