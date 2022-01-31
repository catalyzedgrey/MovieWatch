package com.example.moviewatch.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class Results(var results: List<InnerResults>){

}


@Entity(tableName = "movies_table")
data class InnerResults(
    @PrimaryKey
    val id: String,
    val title: String,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name="poster_path") val posterPath: String?,
    @Json(name="vote_average") val voteAverage: Float?,
    var isFavorite: Boolean = false
)