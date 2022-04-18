package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.navigation.MovieNavigation
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")

       /* val movies = getMovies()
        val favoritesViewModel: FavoritesViewModel by viewModels()

        val favs = favoritesViewModel.getAllMovie()*/

        setContent {
            //val favoritesViewModel: FavoritesViewModel = viewModel()
            //favoritesViewModel.favoriteMovies
            MyApp {
                MovieNavigation()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy called")
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    MovieAppTheme() {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        MovieNavigation()
    }
}