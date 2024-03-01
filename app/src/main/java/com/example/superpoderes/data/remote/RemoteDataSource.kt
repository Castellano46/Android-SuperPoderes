package com.example.superpoderes.data.remote

import com.example.superpoderes.domain.model.Hero
import com.example.superpoderes.domain.model.Serie

interface RemoteDataSource {
    suspend fun getHeroes(): List<Hero>
    suspend fun getHero(heroID: String): Hero
    suspend fun getSeries(heroID: String): List<Serie>
    suspend fun getComics(heroID: String): List<Serie>
}