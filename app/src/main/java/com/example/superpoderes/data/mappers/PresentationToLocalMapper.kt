package com.example.superpoderes.data.mappers

import com.example.superpoderes.data.local.model.LocalSuperhero
import com.example.superpoderes.domain.model.UIHero
import javax.inject.Inject

class PresentationToLocalMapper @Inject constructor() {
    fun mapPresentationSuperhero(superHero: UIHero): LocalSuperhero {
        return LocalSuperhero(superHero.id, superHero.name, superHero.description, superHero.thumbnail, superHero.favorite)
    }
}