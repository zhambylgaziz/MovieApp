package com.example.navigationactivity.api

import com.example.navigationactivity.BuildConfig.API_KEY
import com.example.navigationactivity.model.*
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query



interface MovieApi {
//    Popular movies
    @GET("movie/popular?api_key=" + API_KEY)
    fun getPopularMovies(): Call<MovieResponse>

//    Top rated movies
    @GET("movie/top_rated?api_key=" + API_KEY)
    fun getTopRated(): Call<MovieResponse>

//    Movie
    @GET("movie/{id}?api_key=" + API_KEY)
    fun getMovie(@Path("id") id: String?): Call<Movie>

//    Recommendations for movie
    @GET("movie/{id}/recommendations?api_key=" + API_KEY)
    fun getRecomendations(@Path("id") id: String): Call<MovieResponse>

//    List of Genres
    @GET("genre/movie/list?api_key=" + API_KEY)
    fun getGenres(): Call<GenreResponse>

//    Movies by genre
    @GET("discover/movie?api_key=" + API_KEY)
    fun getMoviesByGenre(@Query("with_genres") genres: String): Call<MovieResponse>

//    Upcoming movies
//    https://api.themoviedb.org/3/movie/upcoming?api_key=c1a56cd2c2fc4c9ce489531a5f5d1cd9
    @GET("movie/upcoming?api_key=" + API_KEY)
    fun getUpcoming(): Call<MovieResponse>

//    Now Playing Movies
//    https://api.themoviedb.org/3/movie/now_playing?api_key=c1a56cd2c2fc4c9ce489531a5f5d1cd9
    @GET("movie/now_playing?api_key=" + API_KEY)
    fun getNowPlaying(): Call<MovieResponse>

//    Cast
//    https://api.themoviedb.org/3/movie/13/credits?api_key=c1a56cd2c2fc4c9ce489531a5f5d1cd9
    @GET("movie/{id}/credits?api_key=" + API_KEY)
    fun getCast(@Path("id") id: String): Call<CastResponse>

//    Similar movies
//    https://api.themoviedb.org/3/movie/13/similar?api_key=c1a56cd2c2fc4c9ce489531a5f5d1cd9
    @GET("movie/{id}/similar?api_key=" + API_KEY)
    fun getSimilar(@Path("id") id: String): Call<MovieResponse>

//    Get videos
//    https://api.themoviedb.org/3/movie/13/videos?api_key=c1a56cd2c2fc4c9ce489531a5f5d1cd9
    @GET("movie/{id}/videos?api_key=" + API_KEY)
    fun getVideos(@Path("id") id: String): Call<VideoResponse>

}