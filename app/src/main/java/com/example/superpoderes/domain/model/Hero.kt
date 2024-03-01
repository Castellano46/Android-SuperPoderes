package com.example.superpoderes.domain.model

data class Response(
    val code: Int,
    val data: Data
)

data class Data(
    val offset: Int,
    val results: List<Hero>
)


data class Hero (
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Thumbnail (
    val path: String,
    val extension: String,
)