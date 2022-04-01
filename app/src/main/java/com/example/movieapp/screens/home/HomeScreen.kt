package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {
    MainContent(navController = navController)
    //MainContent(movieList = getMovies())
}

@Composable
fun MainContent(navController: NavController, movieList: List<Movie> = getMovies()){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn {
            items(items = movieList) { movie ->
                MovieRow(movie = movie) { movieId ->
                    navController.navigate(route = "detailscreen")
                }
            }
        }
    }
}
