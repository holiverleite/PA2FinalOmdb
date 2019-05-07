package com.example.pa2finalomdb

import com.example.pa2finalomdb.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChamadasOmdbAPI {

    @GET("/")
    fun requestFilmByTitle(@Query("t") title: String): Call<Movie>
}