package com.example.ktortmdbapi.data

import com.example.ktortmdbapi.data.models.Movie

interface MoviesRepository {

    suspend fun getPopularMovies(): Resource<List<Movie>>

}