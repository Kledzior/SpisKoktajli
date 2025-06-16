package com.example.mobilecocktails.LocalDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//import com.example.mobilecocktails.User

@Dao
interface CocktailDao {
    @Query("SELECT * FROM cocktails WHERE name = :cocktailName")
    suspend fun getCocktailByName(cocktailName: String): Cocktail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cocktails: List<Cocktail>)

    @Query("SELECT * FROM cocktails")
    suspend fun getAll(): List<Cocktail>

    @Insert
    suspend fun insertAll(vararg cocktails: Cocktail)

    @Query("DELETE FROM cocktails")
    suspend fun deleteAll()

    @Query("SELECT name FROM cocktails")
    suspend fun getAllNames(): List<String>
}