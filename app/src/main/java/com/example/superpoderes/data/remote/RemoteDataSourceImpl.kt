package com.example.superpoderes.data.remote

import com.example.superpoderes.BuildConfig
import com.example.superpoderes.domain.model.Hero
import com.example.superpoderes.domain.model.Serie
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: MarvelApi) : RemoteDataSource {

    override suspend fun getHeroes(): List<Hero> {
        return api.getHeroes(BuildConfig.TS, BuildConfig.APIKEY, BuildConfig.HASH).data.results
    }

    override suspend fun getHero(heroID: String): Hero {
        return api.getHero(heroID, BuildConfig.TS, BuildConfig.APIKEY, BuildConfig.HASH).data.results[0]
    }

    override suspend fun getSeries(heroID: String): List<Serie>{
        return api.getSeries(heroID, BuildConfig.TS, BuildConfig.APIKEY, BuildConfig.HASH).data.results
    }

    override suspend fun getComics(heroID: String): List<Serie> {
        return api.getComics(heroID, BuildConfig.TS, BuildConfig.APIKEY, BuildConfig.HASH).data.results
    }
}