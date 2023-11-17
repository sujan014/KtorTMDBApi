package com.example.ktortmdbapi.di

import com.example.ktortmdbapi.data.MoviesRepository
import com.example.ktortmdbapi.data.MoviesRepositoryImpl
import com.example.ktortmdbapi.data.network.TmdbHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    // need http client
    @Provides
    fun getHttpClient(
        httpClient: TmdbHttpClient
    ): HttpClient = httpClient.getHttpClient()

    // need movies repository
    @Provides
    fun getMoviesRepository(
        impl: MoviesRepositoryImpl
    ): MoviesRepository = impl
}