package com.example.moviewatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviewatch.data.InnerResults
import com.example.moviewatch.data.Results
import com.example.moviewatch.data.MovieDBService
import com.example.moviewatch.data.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeViewModel : ViewModel() {

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

}