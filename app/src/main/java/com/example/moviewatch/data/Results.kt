package com.example.moviewatch.data

import com.squareup.moshi.Json

data class Results(var results: List<InnerResults>){

}


data class InnerResults(
    val id: String,
    val title: String,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name="poster_path") val posterPath: String?,
    @Json(name="vote_average") val voteAverage: Float?
)