package com.example.superpoderes.data.mappers

import com.example.superpoderes.data.local.model.LocalSuperhero
import com.example.superpoderes.domain.model.UIHero
import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor() {
    fun mapLocalSuperheroes(localSuperheroes: List<LocalSuperhero>): List<UIHero> {
        return localSuperheroes.map { mapLocalSuperhero(it) }
    }
    fun mapLocalSuperhero(getHeroesResponse: LocalSuperhero): UIHero {
        return UIHero(getHeroesResponse.id, getHeroesResponse.name,getHeroesResponse.description, getHeroesResponse.photo, getHeroesResponse.favorite)
    }
}