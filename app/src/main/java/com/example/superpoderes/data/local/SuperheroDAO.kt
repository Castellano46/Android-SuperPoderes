package com.example.superpoderes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.superpoderes.data.local.model.LocalSuperhero
import kotlinx.coroutines.flow.Flow

@Dao
interface SuperheroDAO {
    @Query("SELECT * FROM superheroes")
    fun getAll(): Flow<List<LocalSuperhero>>

    @Query("SELECT * FROM superheroes WHERE id = :heroID")
    suspend fun getHero(heroID: String): LocalSuperhero

    @Update
    suspend fun updateHero(hero: LocalSuperhero)

    @Query("SELECT COUNT(id) FROM superheroes")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(vararg hero: LocalSuperhero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVararg(vararg users: LocalSuperhero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(users: List<LocalSuperhero>)

    @Query("DELETE FROM superheroes")
    suspend fun deleteDataSuperheros()
}