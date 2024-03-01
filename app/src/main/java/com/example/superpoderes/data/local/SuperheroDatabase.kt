package com.example.superpoderes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.superpoderes.data.local.model.LocalSuperhero

class SuperHeroDataBase {
    @Database(entities = [LocalSuperhero::class], version = 1)
    abstract class SuperheroDatabase : RoomDatabase() {
        abstract fun superheroDao(): SuperheroDAO
    }
}