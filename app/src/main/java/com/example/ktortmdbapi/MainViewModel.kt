package com.example.ktortmdbapi

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktortmdbapi.data.MoviesRepository
import com.example.ktortmdbapi.data.Resource
import com.example.ktortmdbapi.data.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    private val _movies = MutableStateFlow<Resource<List<Movie>>?>(null)
    val movies: StateFlow<Resource<List<Movie>>?> = _movies     // immutable state flow

    init{
        viewModelScope.launch{
            _movies.value = Resource.Loading
            _movies.value = repository.getPopularMovies()
        }
    }
}