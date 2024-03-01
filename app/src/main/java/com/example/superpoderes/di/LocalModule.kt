package com.example.superpoderes.di

import android.content.Context
import androidx.room.Room
import com.example.superpoderes.data.local.SuperHeroDataBase
import com.example.superpoderes.data.local.SuperheroDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providesSuperheroDatabase(@ApplicationContext context: Context): SuperHeroDataBase.SuperheroDatabase {
        return Room.databaseBuilder(
            context,
            SuperHeroDataBase.SuperheroDatabase::class.java, "superhero-db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesDao(db: SuperHeroDataBase.SuperheroDatabase): SuperheroDAO {
        return db.superheroDao()
    }
}