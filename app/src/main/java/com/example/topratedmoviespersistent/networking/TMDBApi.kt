package com.example.topratedmoviespersistent.networking

import com.example.topratedmoviespersistent.model.MovieDetails
import com.example.topratedmoviespersistent.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Path


interface TMDBApi {
    @GET("https://api.themoviedb.org/3/discover/movie?api_key=92f2c838a38c8f850e0d1f69debeba77&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    suspend fun getMovieList():MovieList

    @GET("https://api.themoviedb.org/3/movie/{id}?api_key=92f2c838a38c8f850e0d1f69debeba77")
    suspend fun getMovieDetailsById(@Path("id") id:String):MovieDetails

}