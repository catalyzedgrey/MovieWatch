package com.example.moviewatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviewatch.data.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    var trendingMovies: MutableLiveData<Results> = Repository.trendingMovies
    var searchResults: MutableLiveData<Results> = Repository.searchResults
    @Inject lateinit var movieDao: MovieDao

    init {
        CoroutineScope(Dispatchers.IO).launch{
            Repository.getTrendingMovie()
        }

    }

    fun onQueryTextSubmit(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            Repository.searchMovies(query, movieDao)
        }

    }

    fun favoriteMovie(movie: InnerResults) {
        viewModelScope.launch {
            Repository.addToFavorites(movie, movieDao)
        }

    }

}