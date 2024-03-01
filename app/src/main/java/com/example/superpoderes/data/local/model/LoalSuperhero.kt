package com.example.superpoderes.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superheroes")
data class LocalSuperhero(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean = false,
)