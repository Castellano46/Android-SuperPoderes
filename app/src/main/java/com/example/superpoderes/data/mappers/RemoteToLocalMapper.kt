package com.example.superpoderes.data.mappers

import com.example.superpoderes.data.local.model.LocalSuperhero
import com.example.superpoderes.domain.model.Hero
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor(){
    fun mapGetHeroesResponse(getHeroesResponse: List<Hero>): List<LocalSuperhero> {
        return getHeroesResponse.map { mapGetHeroResponse(it) }
    }
    private fun mapGetHeroResponse(hero: Hero): LocalSuperhero {
        return LocalSuperhero(hero.id.toString(), hero.name, hero.description, hero.thumbnail.path + "."+hero.thumbnail.extension, false)
    }
}