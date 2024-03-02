package com.example.superpoderes.data.remote.fakes

import com.example.superpoderes.data.remote.RemoteDataSource
import com.example.superpoderes.domain.model.Hero
import com.example.superpoderes.domain.model.Serie
import com.example.superpoderes.utils.generateGetHeroesResponse
import com.example.superpoderes.utils.generateOneSuperhero
import com.example.superpoderes.utils.generatePublishList

class FakeRemoteDataSource: RemoteDataSource {

    override suspend fun getHeroes():  List<Hero> {
        return generateGetHeroesResponse(15)
    }

    override suspend fun getHero(idHero: String): Hero {
        return generateOneSuperhero()
    }

    override suspend fun getSeries(heroID: String): List<Serie> {
        return generatePublishList()
    }

    override suspend fun getComics(heroID: String): List<Serie> {
        return generatePublishList()
    }
}