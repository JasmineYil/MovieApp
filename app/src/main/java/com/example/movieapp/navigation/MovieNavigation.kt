package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.detail.DetailScreen
import com.example.movieapp.screens.favorite.FavoritesScreen
import com.example.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){
        composable(MovieScreens.HomeScreen.name) { HomeScreen(navController = navController)}

        // url: www.domain.com/detailscreen/movie=12...wenn man ein ganz bestimmten Pfad will
        composable(MovieScreens.DetailScreen.name + "/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ){  backStackEntry ->
            DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movieId")
            )
        }
        composable(MovieScreens.FavoritesScreen.name) { FavoritesScreen(navController = navController)}
    }
        //Add more routes and screens here
}

