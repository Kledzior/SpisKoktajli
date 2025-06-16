package com.example.mobilecocktails.LocalDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//import com.example.mobilecocktails.User

@Entity(tableName = "cocktails")
data class Cocktail(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "ingredients") val ingredients: String?,
    @ColumnInfo(name = "preparation") val preparation: String?
)

