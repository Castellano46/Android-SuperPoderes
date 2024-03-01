package com.example.superpoderes.domain.model

data class UIHero (
    val id: String,
    val name: String,
    val description: String,
    val thumbnail: String,
    var favorite: Boolean
)