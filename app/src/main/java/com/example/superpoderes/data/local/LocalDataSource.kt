package com.example.superpoderes.data.local

import com.example.superpoderes.data.local.model.LocalSuperhero
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    suspend fun getHeroes(): Flow<List<LocalSuperhero>>
    suspend fun insertHeroes(localSuperheros: List<LocalSuperhero>)
    suspend fun insertHero(localSuperhero: LocalSuperhero)
    suspend fun updateHero(localSuperhero: LocalSuperhero)
    suspend fun dbIsEmpty(): Boolean
    suspend fun getHero(heroID: String): LocalSuperhero
    suspend fun deleteData()
}