package com.example.ktortmdbapi.ui.movies

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ktortmdbapi.data.models.Movie
import net.simplifiedcoding.tmdbmovies.ui.theme.spacing

@Composable
fun MovieList(
    movies: List<Movie>,
) {
    LazyColumn {
        items(movies) { item ->
            MovieItem(item)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    //val spacing = spacing
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp)
            .clip(RoundedCornerShape(5.dp))
            .shadow(elevation = 1.dp)
            .background(color = Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Log.d("MOVIELOG", "$movie")
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.fullPosterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .weight(0.4f)
            )
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(start = 3.dp)
            ) {
                Text(
                    text = movie.originalTitle,
                    style = MaterialTheme.typography.h5,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = movie.overview,
                    fontSize = 15.sp,
                    color = Color.White,
                    maxLines = 7,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(5.dp))
                        .padding(spacing.small)
                        .background(Color.Yellow),
                    text = "IMDB ${movie.voteAverage}",
                    style = MaterialTheme.typography.h6,
                    color = Color.Black,
                    maxLines = 7,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            strokeWidth = 10.dp
        )
    }
}