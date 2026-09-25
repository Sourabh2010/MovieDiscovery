package com.example.moviediscovery.presentation.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.moviediscovery.domain.model.Movie
import com.example.moviediscovery.domain.util.Result
import com.example.moviediscovery.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val popularMoviesState by homeScreenViewModel.popularMoviesState.collectAsState()
    val trendingMoviesState by homeScreenViewModel.trendingMoviesState.collectAsState()
    val topRatedMoviesState by homeScreenViewModel.topRatedMoviesState.collectAsState()
    val searchQuery by remember { mutableStateOf("") }


    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Movie Discovery", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.height(32.dp)
                    )
                }
            }
        )

    }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            item {
                Row {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text("Popular Movies", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
                when (val state = popularMoviesState) {
                    is Result.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is Result.Success -> {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            items(state.data) { movie ->
                                MovieCard(movie = movie, onClick = {
                                    navHostController.navigate(
                                        Routes.MovieDetailScreen(movie.id)
                                    )
                                })

                            }
                        }
                    }

                    is Result.Failure -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Error: ${state.message}",
                                    color = Color.Red
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextButton(
                                    onClick = { homeScreenViewModel.loadPopularMovies() }
                                ) {
                                    Text("Retry")
                                }
                            }

                        }
                    }

                    Result.Idle -> {}
                }
            }



            item {
                Row {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text("Trending Movies", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }

                when (val state = trendingMoviesState) {
                    is Result.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is Result.Success -> {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            items(state.data) { movie ->
                                MovieCard(movie = movie, onClick = {
                                    navHostController.navigate(
                                        Routes.MovieDetailScreen(movie.id)
                                    )
                                })

                            }
                        }
                    }

                    is Result.Failure -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Error: ${state.message}",
                                    color = Color.Red
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextButton(
                                    onClick = { homeScreenViewModel.loadTrendingMovies() }
                                ) {
                                    Text("Retry")
                                }
                            }

                        }
                    }

                    Result.Idle -> {}
                }
            }


            item {
                Row {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text("Top Rated Movies", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }

                when (val state = topRatedMoviesState) {
                    is Result.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is Result.Success -> {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            items(state.data) { movie ->
                                MovieCard(movie = movie, onClick = {
                                    navHostController.navigate(
                                        Routes.MovieDetailScreen(movie.id)
                                    )
                                })

                            }
                        }
                    }

                    is Result.Failure -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Error: ${state.message}",
                                    color = Color.Red
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                TextButton(
                                    onClick = { homeScreenViewModel.loadTopRatedMovies() }
                                ) {
                                    Text("Retry")
                                }
                            }

                        }
                    }

                    Result.Idle -> {}
                }
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movie, onClick: () -> Unit) {
    val context = LocalContext.current

    val imageUrl = movie.posterPath?.let {
        "https://image.tmdb.org/t/p/w500$it"
    }
    Card(
        modifier = Modifier
            .size(width = 145.dp, height = 225.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(context).data(imageUrl).crossfade(true)
                    .build(),

                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Fit,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                    }
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No Image",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }

            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = movie.title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(Modifier.height(2.dp))
            movie.overview?.let {
                Text(
                    text = it,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Release Date: ${movie.releaseDate}",
                fontSize = 10.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Rating: ${movie.voteAverage}",
                fontSize = 10.sp,
                color = Color.Gray
            )
        }
    }
}
