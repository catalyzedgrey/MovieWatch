package com.example.moviewatch.data

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

@ViewModelScoped
object Repository {

    var trendingMovies: MutableLiveData<Results> = MutableLiveData()
    var searchResults: MutableLiveData<Results> = MutableLiveData()

    @Inject lateinit var moviesDao: MovieDao

    suspend fun getTrendingMovie(): MutableLiveData<Results> {
        withContext(Dispatchers.IO) {
            trendingMovies.postValue(MovieDBService.MovieApi.movieService.getTrendingMoviesThisWeek())
        }
        return trendingMovies
    }

    suspend fun searchMovies(query: String, movieDao: MovieDao): MutableLiveData<Results> {
        CoroutineScope(Dispatchers.IO).launch {
            val results = MovieDBService.MovieApi.movieService.searchMovies(query)

            try {
                results.results.forEach {

                    val movie = movieDao.findMovieById(it.id)
                    if (movie!= null && it.id == movie.id) {
                        it.isFavorite = true
                    }
                }
                searchResults.postValue(results)
            } catch (exception: Exception) {

            }
        }

        return searchResults
    }

//    suspend fun compareResultsToFavorites(innerResults: Results, movieDao: MovieDao){
//        CoroutineScope(Dispatchers.IO).launch{
//
//
//
//        }
//    }

    suspend fun addToFavorites(movie: InnerResults, movieDao: MovieDao) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.insertMovie(movie)
        }

    }

//    suspend fun favoriteMovie(movie: InnerResults) {
//
//        withContext(Dispatchers.IO){
//            temp.movieDao.insertMovie(movie)
//        }
//    }
}
