package com.example.ktortmdbapi.data

import com.example.ktortmdbapi.data.models.Movie
import com.example.ktortmdbapi.data.models.PopularMovies
import com.example.ktortmdbapi.data.network.BASE_URL
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import javax.inject.Inject

// pass the HttpClient -> ktor.client
//https://api.themoviedb.org/3/movie/popular

private const val POPULAR_MOVIES = "${BASE_URL}/popular"

class MoviesRepositoryImpl @Inject constructor( // constructor injection
    private val httpClient: HttpClient
): MoviesRepository {
    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        // make the network call
        return try{
            Resource.Success( // success -> pass list of movies by performing an api request to url
                httpClient.get<PopularMovies>{
                    // from lambda get movies
                    url(POPULAR_MOVIES)
                }.results
            )
        } catch(e: Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}