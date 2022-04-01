package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieNavigation
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.home.MainContent
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.widgets.MovieRow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    var showMenu by remember{
        mutableStateOf(false)
    }
    MovieAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Movies") },
                    actions = {
                        IconButton(onClick = { showMenu = !showMenu}) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                        }
                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = { /*TODO*/ }) {
                                Row{
                                    Icon(imageVector = Icons.Default.Favorite,
                                        contentDescription = "my favorites",
                                        modifier = Modifier.padding(4.dp))
                                    Text(text = "Favorites",
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .width(100.dp)
                                    )
                                }
                            }
                        }
                    }
                )
            }
        ){
            content()
        }
    }
}
/*
@Composable
fun MainContent(movieList: List<Movie>){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn {
            items(items = movieList) { movie ->
                MovieRow(movie = movie)
            }
        }
    }
}
 */

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation()
    }
}