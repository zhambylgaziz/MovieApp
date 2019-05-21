package com.example.navigationactivity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.navigationactivity.fragments.*
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val manager = supportFragmentManager
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                createHomeFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_movies -> {
                createMoviesFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favourites -> {
                createFavouritesFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        createHomeFragment()
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        supportActionBar!!.hide()

    }

    private fun createHomeFragment(){
        val transaction = manager.beginTransaction()
        val fragment = HomeFragment()
        transaction.replace(R.id.fragmentHolder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun createMoviesFragment(){
        val transaction = manager.beginTransaction()
        val fragment = MoviesFragment()
        transaction.replace(R.id.fragmentHolder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun createFavouritesFragment(){
        val transaction = manager.beginTransaction()
        val user = firebaseAuth.currentUser
        if (user != null) {
            val fragment = FavouritesFragment()
            transaction.replace(R.id.fragmentHolder, fragment)
        }
        else{
            val fragment = LoginFragment()
            transaction.replace(R.id.fragmentHolder, fragment)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
