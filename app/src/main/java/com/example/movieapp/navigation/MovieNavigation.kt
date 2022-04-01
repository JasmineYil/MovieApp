package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homescreen"){
        composable("homescreen") { HomeScreen(navController = navController)}
        //Add more routes and screens here

    }
}