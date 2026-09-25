package com.example.moviediscovery.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviediscovery.domain.model.Movie
import com.example.moviediscovery.domain.model.MovieDetails
import com.example.moviediscovery.domain.usecase.GetMovieDetailsUseCase
import com.example.moviediscovery.domain.usecase.GetPopularMoviesUseCase
import com.example.moviediscovery.domain.usecase.GetTopRatedMoviesUseCase
import com.example.moviediscovery.domain.usecase.GetTrendingMoviesUseCase
import com.example.moviediscovery.domain.usecase.SearchMovieUseCase
import com.example.moviediscovery.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow<Result<MovieDetails>>(Result.Idle)
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private val _popularMoviesState = MutableStateFlow<Result<List<Movie>>>(Result.Idle)
    val popularMoviesState = _popularMoviesState.asStateFlow()

    private val _trendingMoviesState = MutableStateFlow<Result<List<Movie>>>(Result.Idle)
    val trendingMoviesState = _trendingMoviesState.asStateFlow()

    private val _topRatedMoviesState = MutableStateFlow<Result<List<Movie>>>(Result.Idle)
    val topRatedMoviesState = _topRatedMoviesState.asStateFlow()

    private val _searchedMoviesState = MutableStateFlow<Result<List<Movie>>>(Result.Idle)
    val searchedMoviesState = _searchedMoviesState.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadPopularMovies()
        loadTrendingMovies()
        loadTopRatedMovies()
    }

    fun retryLoadingPopularMovies() {
        loadPopularMovies()
    }

    fun retryLoadingTrendingMovies() {
        loadTrendingMovies()

    }

    fun retryLoadingTopRatedMovies() {
        loadTopRatedMovies()
    }


    fun loadPopularMovies() {
        _popularMoviesState.value = Result.Loading
        viewModelScope.launch {
            try {
                val result = getPopularMoviesUseCase()
                _popularMoviesState.value = result
            } catch (e: Exception) {
                _popularMoviesState.value = Result.Failure(e.message ?: "Unknown Error")
            }
        }
    }

    fun loadTrendingMovies() {
        _trendingMoviesState.value = Result.Loading
        viewModelScope.launch {
            try {
                val result = getTrendingMoviesUseCase()
                _trendingMoviesState.value = result
            } catch (e: Exception) {
                _trendingMoviesState.value = Result.Failure(e.message ?: "Unknown Error")
            }

        }
    }

    fun loadTopRatedMovies() {
        _topRatedMoviesState.value = Result.Loading
        viewModelScope.launch {
            try {
                val result = getTopRatedMoviesUseCase()
                _topRatedMoviesState.value = result
            } catch (e: Exception) {
                _topRatedMoviesState.value = Result.Failure(e.message ?: "Unknown Error")

            }
        }
    }


    fun searchMovies(query: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(300)
            _searchedMoviesState.value = Result.Loading
            try {
                val result = searchMovieUseCase(query)
                _searchedMoviesState.value = result
            } catch (e: Exception) {
                _searchedMoviesState.value = Result.Failure(e.message ?: "Unknown Error")
            }

        }
    }

    fun loadMovieDetails(movieId: Int) {

        viewModelScope.launch {

            _movieDetailsState.value = Result.Loading
            try {
                val result = getMovieDetailsUseCase(movieId)
                _movieDetailsState.value = result
            } catch (e: Exception) {
                _movieDetailsState.value = Result.Failure(e.message ?: "Unknown Error")
            }
        }
    }
}


