package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodels.FavoritesViewModel
import com.example.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController = rememberNavController(), viewModel: FavoritesViewModel) {

    var showMenu by remember{
        mutableStateOf(false)
    }
    MovieAppTheme {
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu}) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(route = MovieScreens.FavoritesScreen.name) }) {
                            Row{
                                Icon(imageVector = Icons.Default.Favorite,
                                    contentDescription = "my favorites",
                                    modifier = Modifier.padding(4.dp))
                                Text(text = "Favorites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp))
                            }
                        }
                    }
                })
            }
        ){
            MainContent(
                navController = navController,
                movieList = getMovies(),
                onAddClick = { movie -> viewModel.addFavorites(movie) },
                onDeleteClick = { movie -> viewModel.removeFavorites(movie) },
                favorite = { movie -> viewModel.isFavorite(movie = movie) },
                favoriteIcon = true
            )
        }
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie>,
    onAddClick: (Movie) -> Unit = {},
    onDeleteClick: (Movie) -> Unit = {},
    favorite: @Composable (Movie) -> Boolean = { false },
    favoriteIcon: Boolean,
){
    /*
    LazyColumn {
        items(items = movieList) { movie ->
            MovieRow(movie = movie) { movieId ->
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
            }
        }
    }
     */
    LazyColumn {
        items(movieList) { movie ->
            MovieRow(
                movie = movie,
                onItemClick = { movieId -> navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")},
                onAddClick = { onAddClick(movie) },
                onDeleteClick = { onDeleteClick(movie) },
                favorite = favorite(movie),
                favoriteIcon = favoriteIcon
            )
        }
    }
}


/*
@Composable
fun MainContent(navController: NavController, movieList: List<Movie>, favoritesViewModel: FavoritesViewModel){
    LazyColumn {
        items(items = movieList) { movie ->
            MovieRow(movie = movie,
                onItemClick = { movieId ->
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                    {
                        FavoriteIcon(
                            movie = movie,
                            isFave = favoritesViewModel.isFavorite(movie))
                        { m ->
                            if(favoritesViewModel.isFavorite(m)){
                                favoritesViewModel.removeFromFavorites(m)
                            } else {
                                favoritesViewModel.addToFavorites(m)
                            }
                        }
                    }
                }

        }
    }
}
*/
