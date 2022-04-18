package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.ui.theme.MovieAppTheme


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie = getMovies()[0],
             onItemClick: (String) -> Unit = {},

    ){
    //             content: @Composable () -> Unit = {}
    var movieDescriptions by remember{
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ){  Row(verticalAlignment = Alignment.CenterVertically){
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp)
            ){
                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0] ,
                        builder = {
                            transformations(CircleCropTransformation())
                        }),
                    contentDescription = "movie pic")
            }
            Column(modifier = Modifier.padding(0.dp, 10.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}")
                Text(text = "Year: ${movie.year}")

                AnimatedVisibility(visible = movieDescriptions,
                    enter = expandVertically(expandFrom = Alignment.Top),
                    exit = shrinkVertically()
                ) {
                    Column (modifier = Modifier.padding(10.dp, 5.dp)) {
                        Text(text = "Plot: ${movie.plot}")
                        Divider(thickness = 1.dp)
                        Text(text = "Genre: ${movie.genre}")
                        Text(text = "Actors: ${movie.actors}")
                        Text(text = "Rating: ${movie.rating}")
                    }
                }
                IconToggleButton(checked = movieDescriptions, onCheckedChange = {movieDescriptions = it}) {
                    if(movieDescriptions){
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "ArrowDown")
                    }
                    else {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "ArrowUp" )
                    }
                }
            }
            // TODO: Soll nicht in FAVORITESSCREEN angezeigt werden
            Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                FavoriteIcon()
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]){
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {
                Image(painter = rememberImagePainter(data = image),
                    contentDescription = "movie image")
            }
        }
    }
}

@Composable
fun FavoriteIcon(

){
    var iconClick by remember{
        mutableStateOf(false)
    }
    IconToggleButton(checked = iconClick, onCheckedChange = {iconClick = it}) {
        if(iconClick){
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "FavoriteClicked",
                modifier = Modifier.padding(4.dp),
                Color.Cyan)
        }
        else {
            Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "FavoriteNotClicked",
                modifier = Modifier.padding(4.dp),
                Color.Cyan)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        MovieRow()
    }
}