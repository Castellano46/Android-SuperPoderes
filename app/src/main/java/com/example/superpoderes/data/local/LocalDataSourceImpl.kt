package com.example.superpoderes.data.local

import com.example.superpoderes.data.local.model.LocalSuperhero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: SuperheroDAO): LocalDataSource {
    override suspend fun getHeroes(): Flow<List<LocalSuperhero>> {
        return dao.getAll()
    }

    override suspend fun insertHeroes(localSuperheros: List<LocalSuperhero>){
        dao.insertAllList(localSuperheros)
    }

    override suspend fun dbIsEmpty(): Boolean {
        return  dao.getCount() == 0
    }

    override suspend fun insertHero(localSuperhero: LocalSuperhero) {
        dao.insertHero(localSuperhero)
    }

    override suspend fun updateHero(localSuperhero: LocalSuperhero) {
        dao.updateHero(localSuperhero)
    }

    override suspend fun getHero(heroID: String): LocalSuperhero {
        return dao.getHero(heroID)
    }

    override suspend fun deleteData() {
        dao.deleteDataSuperheros()
    }
}