package com.example.movieapp.screens.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.viewmodels.FavoritesViewModel
import com.example.movieapp.widgets.MovieRow

//@Preview(showBackground = true)
@Composable
fun FavoritesScreen(navController: NavController = rememberNavController(),
                    viewModel: FavoritesViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack() //go back to last screen
                        })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "My Favorite Movies", style = MaterialTheme.typography.h6)
                }
            }
        })
    {
        MainContent(
            movieList = viewModel.favoriteMovies,
            navController = navController,
            onAddClick = { movie -> viewModel.addFavorites(movie)},
            onDeleteClick = { movie -> viewModel.removeFavorites(movie) },
            favoriteIcon = false
            )
        //Hardcoded favorites: MainContent(movieList = getMovies().subList(4, 7))
    }
}

@Composable
fun MainContent(
    movieList: List<Movie>,
    navController: NavController,
    onAddClick: (Movie) -> Unit = {},
    onDeleteClick: (Movie) -> Unit = {},
    favorite: @Composable (Movie) -> Boolean = { false },
    favoriteIcon: Boolean
) {
    LazyColumn {
        items(movieList) { movie ->
            MovieRow(
                movie = movie,
                onItemClick = { movieId -> navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId") },
                onAddClick = { onAddClick(movie) },
                onDeleteClick = { onDeleteClick(movie) },
                favorite = favorite(movie),
                favoriteIcon = favoriteIcon
            )
        }
    }
}