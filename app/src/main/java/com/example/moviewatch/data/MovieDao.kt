package com.example.moviewatch.data

import androidx.room.*

@Dao
interface MovieDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(innerResults: InnerResults)

    @Query("SELECT * FROM movies_table")
    suspend fun getAllMovies():List<InnerResults>

    @Query("SELECT * FROM movies_table WHERE id Like :movieId")
    fun findMovieById(movieId: String): InnerResults

    @Update
    suspend fun updateMovie(innerResults: InnerResults)

    @Delete
    suspend fun deleteMovie(innerResults: InnerResults)
}