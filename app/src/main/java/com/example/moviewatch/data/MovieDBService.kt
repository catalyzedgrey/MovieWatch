package com.example.moviewatch.data


import com.example.moviewatch.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.json.JSONObject
import org.json.JSONStringer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MovieDBService {

    @GET("search/multi")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DB_API_TOKEN
    ): Results

    @GET("trending/movie/week")
    suspend fun getTrendingMoviesThisWeek(@Query("api_key") apiKey: String = BuildConfig.THE_MOVIE_DB_API_TOKEN):Results

    object MovieApi {
        val movieService: MovieDBService by lazy {
            retrofit.create(MovieDBService::class.java)
        }
    }


}