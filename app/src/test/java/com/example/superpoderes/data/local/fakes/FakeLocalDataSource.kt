package com.example.superpoderes.data.local.fakes

import android.util.Log
import com.example.superpoderes.data.local.LocalDataSource
import com.example.superpoderes.data.local.model.LocalSuperhero
import com.example.superpoderes.utils.generateLocalSuperheroes
import com.example.superpoderes.utils.generateOneLocalSuperhero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeLocalDataSource @Inject constructor() : LocalDataSource {

    override suspend fun getHeroes(): Flow<List<LocalSuperhero>> {
        return generateLocalSuperheroes()
    }

    override suspend fun insertHero(localSuperhero: LocalSuperhero) {
        Log.d("testing", "Hero Inserted")
    }

    override suspend fun updateHero(localSuperhero: LocalSuperhero) {
        Log.d("testing", "Hero Updated")
    }

    override suspend fun dbIsEmpty(): Boolean {
        return true
    }

    override suspend fun getHero(heroID: String): LocalSuperhero {
        return generateOneLocalSuperhero()
    }

    override suspend fun deleteData() {
        Log.d("testing", "Data deleted")
    }

    override suspend fun insertHeroes(localSuperheros: List<LocalSuperhero>) {

    }
}