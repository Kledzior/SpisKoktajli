package com.example.mobilecocktails.LocalDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cocktail::class], version = 2)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao

    companion object {
        @Volatile
        private var INSTANCE: CocktailDatabase? = null

        fun getDatabase(context: Context): CocktailDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                                context.applicationContext,
                                CocktailDatabase::class.java,
                                "cocktails-db"
                            ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
        }
    }
}
