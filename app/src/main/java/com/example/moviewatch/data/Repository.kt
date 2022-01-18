package com.example.moviewatch.data

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

object Repository {

    var trendingMovies: MutableLiveData<Results> = MutableLiveData()

    var searchResults: MutableLiveData<Results> = MutableLiveData()


    suspend fun getTrendingMovie(): MutableLiveData<Results> {
        withContext(Dispatchers.IO) {
            trendingMovies.postValue(MovieDBService.MovieApi.movieService.getTrendingMoviesThisWeek())
        }
        return trendingMovies
    }

    suspend fun searchMovies(query: String): MutableLiveData<Results> {
        CoroutineScope(Dispatchers.IO).launch {
            searchResults.postValue(MovieDBService.MovieApi.movieService.searchMovies(query))
        }
        return searchResults
    }
}