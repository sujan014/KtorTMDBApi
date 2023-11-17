package com.example.ktortmdbapi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.ktortmdbapi.data.Resource
import com.example.ktortmdbapi.data.models.Movie
import com.example.ktortmdbapi.ui.movies.LoadingScreen
import com.example.ktortmdbapi.ui.movies.MovieList
import com.example.ktortmdbapi.ui.theme.KtorTMDBApiTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //private val mainViewModel by viewModels<MainViewModel>()
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // with the help of viewmodel, observe the movies and collect it as state
            val movies: State<Resource<List<Movie>>?> = mainViewModel.movies.collectAsState()
            val context = LocalContext.current
            KtorTMDBApiTheme {

                // if not null
                movies.value?.let{ resourceState ->
                    when(resourceState){
                        is Resource.Failure -> {
                            //Toast
                            Toast.makeText(context, "${resourceState.exception.message!!}", Toast.LENGTH_SHORT).show()
                        }
                        is  Resource.Loading -> {
                            // create progressbar
                            LoadingScreen()
                        }
                        is Resource.Success -> {
                            // display list
                            MovieList(resourceState.result)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorTMDBApiTheme {

    }
}