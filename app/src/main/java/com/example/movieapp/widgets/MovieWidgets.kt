package com.example.movieapp.widgets

import android.service.autofill.OnClickAction
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie = getMovies()[0],
            onItemClick: (String) -> Unit = {}
            ){
    var movieDescriptions by remember{
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .fillMaxHeight()
        .clickable {
                   onItemClick(movie.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ){
        Row(verticalAlignment = Alignment.CenterVertically){
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                elevation = 6.dp
            ){
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie pic")
            }
            Column(modifier = Modifier.padding(0.dp, 10.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}")
                Text(text = "Year: ${movie.year}")

                AnimatedVisibility(visible = movieDescriptions,
                    enter = expandVertically(expandFrom = Alignment.Top),
                    exit = shrinkVertically()
                ) {
                    // Alle Möglichen Composables aufrufen
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
                /*
                Andere Möglichkeit:
                IconButton(onClick = { movieDescriptions = !movieDescriptions}) { ... }
                 */
            }
        }
    }
}