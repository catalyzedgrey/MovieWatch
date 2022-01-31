package com.example.moviewatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviewatch.data.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel constructor() : ViewModel() {

    var trendingMovies: MutableLiveData<Results> = Repository.trendingMovies
    var searchResults: MutableLiveData<Results> = Repository.searchResults

    init {
        CoroutineScope(Dispatchers.IO).launch{
            Repository.getTrendingMovie()
        }

    }

    fun onQueryTextSubmit(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            Repository.searchMovies(query)
        }

    }

    fun favoriteMovie(movie: InnerResults, movieDao: MovieDao) {
        CoroutineScope(Dispatchers.IO).launch {
            //Repository.favoriteMovie(movie)
            movieDao.insertMovie(movie)
        }

    }

}