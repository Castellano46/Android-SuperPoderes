package com.example.superpoderes.data.remote

import com.example.superpoderes.domain.model.Response
import com.example.superpoderes.domain.model.ResponseSerie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("v1/public/characters")
    suspend fun getHeroes(@Query("ts") ts: String, @Query("apikey") apiKey: String,  @Query("hash") hash: String): Response
    @GET("v1/public/characters/{id}")
    suspend fun getHero(@Path("id") heroID: String, @Query("ts") ts: String, @Query("apikey") apiKey: String, @Query("hash") hash: String): Response
    @GET("v1/public/characters/{id}/series")
    suspend fun getSeries(@Path("id") heroID: String, @Query("ts") ts: String, @Query("apikey") apiKey: String, @Query("hash") hash: String): ResponseSerie
    @GET("v1/public/characters/{id}/comics")
    suspend fun getComics(@Path("id") heroID: String, @Query("ts") ts: String, @Query("apikey") apiKey: String, @Query("hash") hash: String): ResponseSerie
}
