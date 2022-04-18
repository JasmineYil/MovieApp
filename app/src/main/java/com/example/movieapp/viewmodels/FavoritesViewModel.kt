package com.example.movieapp.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Movie

class FavoritesViewModel : ViewModel() {
    private val _favoriteMovies = mutableStateListOf<Movie>()

    val favoriteMovies: List<Movie>
        get() = _favoriteMovies

    fun addFavorites(movie: Movie){
        if(!exists(movie = movie)){
            _favoriteMovies.add(movie)
        }
    }

    fun removeFavorites(movie: Movie){
        _favoriteMovies.remove(movie)
    }

    private fun exists(movie: Movie) : Boolean {
        return _favoriteMovies.any {movies -> movies.id == movie.id }
    }

    fun isFavorite(movie: Movie) : Boolean{
        return exists(movie = movie)
    }
}
